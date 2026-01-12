package com.example.lease_cal.service;

import com.example.lease_cal.dto.*;
import com.example.lease_cal.entity.*;
import com.example.lease_cal.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class LeadService {
    
    @Autowired
    private LeadRepository leadRepository;
    
    @Autowired
    private PartyRepository partyRepository;
    
    @Autowired
    private IncomeSourceRepository incomeSourceRepository;
    
    @Autowired
    private RelatedPartyRepository relatedPartyRepository;
    
    /**
     * Save a lead with parties, identifications, and addresses only
     * Income sources and related parties should be saved separately
     * 
     * @param leadRequestDTO The lead request DTO containing lead and party data
     * @return LeadDTO with saved data including IDs
     */
    public LeadDTO saveLeadWithChildren(LeadRequestDTO leadRequestDTO) {
        // Create and save Lead entity
        Lead lead = new Lead();
        lead.setLeadName(leadRequestDTO.getLeadName());
        lead.setCreationType(leadRequestDTO.getCreationType());
        lead.setCreatedBy(leadRequestDTO.getCreatedBy());
        lead.setCreatedDate(LocalDate.now());
        
        // Save lead first to get the ID
        lead = leadRepository.save(lead);
        
        // Convert and save parties with identifications and addresses only
        if (leadRequestDTO.getParties() != null && !leadRequestDTO.getParties().isEmpty()) {
            List<Party> parties = new ArrayList<>();
            for (PartyRequestDTO partyRequestDTO : leadRequestDTO.getParties()) {
                Party party = convertToPartyEntity(partyRequestDTO, lead);
                parties.add(party);
            }
            lead.setParties(parties);
        }
        
        // Save lead again with parties (cascade will save identifications and addresses)
        lead = leadRepository.save(lead);
        
        // Convert to DTO and return
        return convertToLeadDTO(lead);
    }
    
    /**
     * Save income sources for a party
     * 
     * @param partyId The ID of the party
     * @param incomeSourceRequestDTOs List of income source request DTOs
     * @return List of saved IncomeSourceDTOs
     */
    public List<IncomeSourceDTO> saveIncomeSources(Long partyId, List<IncomeSourceRequestDTO> incomeSourceRequestDTOs) {
        // Find the party
        Party party = partyRepository.findById(partyId)
                .orElseThrow(() -> new RuntimeException("Party not found with id: " + partyId));
        
        // Convert and save income sources
        List<IncomeSource> incomeSources = new ArrayList<>();
        for (IncomeSourceRequestDTO incomeRequestDTO : incomeSourceRequestDTOs) {
            IncomeSource incomeSource = new IncomeSource();
            incomeSource.setParty(party);
            incomeSource.setIncomeType(incomeRequestDTO.getIncomeType());
            incomeSource.setConsiderForRepayment(incomeRequestDTO.getConsiderForRepayment());
            incomeSources.add(incomeSource);
        }
        
        // Save all income sources
        incomeSources = incomeSourceRepository.saveAll(incomeSources);
        
        // Convert to DTOs and return
        return incomeSources.stream()
                .map(this::convertToIncomeSourceDTO)
                .collect(Collectors.toList());
    }
    
    /**
     * Save related parties for a lead
     * 
     * @param leadId The ID of the lead
     * @param relatedPartyRequestDTOs List of related party request DTOs
     * @return List of saved RelatedPartyDTOs
     */
    public List<RelatedPartyDTO> saveRelatedParties(Long leadId, List<RelatedPartyRequestDTO> relatedPartyRequestDTOs) {
        // Find the lead
        Lead lead = leadRepository.findById(leadId)
                .orElseThrow(() -> new RuntimeException("Lead not found with id: " + leadId));
        
        // Convert and save related parties
        List<RelatedParty> relatedParties = new ArrayList<>();
        for (RelatedPartyRequestDTO relatedPartyRequestDTO : relatedPartyRequestDTOs) {
            RelatedParty relatedParty = convertToRelatedPartyEntity(relatedPartyRequestDTO, lead);
            relatedParties.add(relatedParty);
        }
        
        // Save all related parties
        relatedParties = relatedPartyRepository.saveAll(relatedParties);
        
        // Convert to DTOs and return
        return relatedParties.stream()
                .map(this::convertToRelatedPartyDTO)
                .collect(Collectors.toList());
    }
    
    /**
     * Convert RelatedPartyRequestDTO to RelatedParty entity
     * Note: This method expects that parties are already saved and have IDs
     */
    private RelatedParty convertToRelatedPartyEntity(RelatedPartyRequestDTO relatedPartyRequestDTO, Lead lead) {
        RelatedParty relatedParty = new RelatedParty();
        relatedParty.setLead(lead);
        relatedParty.setRelationshipDescription(relatedPartyRequestDTO.getRelationshipDescription());
        relatedParty.setConsiderCrib(relatedPartyRequestDTO.getConsiderCrib());
        relatedParty.setConsiderAdvanceAnalysis(relatedPartyRequestDTO.getConsiderAdvanceAnalysis());
        
        // Find main party and related party by their IDs
        if (relatedPartyRequestDTO.getMainPartnerId() != null) {
            Party mainParty = partyRepository.findById(relatedPartyRequestDTO.getMainPartnerId())
                    .orElseThrow(() -> new RuntimeException("Main party not found with id: " + relatedPartyRequestDTO.getMainPartnerId()));
            relatedParty.setMainParty(mainParty);
        }
        
        if (relatedPartyRequestDTO.getRelatedPartnerId() != null) {
            Party relatedPartyEntity = partyRepository.findById(relatedPartyRequestDTO.getRelatedPartnerId())
                    .orElseThrow(() -> new RuntimeException("Related party not found with id: " + relatedPartyRequestDTO.getRelatedPartnerId()));
            relatedParty.setRelatedParty(relatedPartyEntity);
        }
        
        return relatedParty;
    }
    
    /**
     * Convert PartyRequestDTO to Party entity
     */
    private Party convertToPartyEntity(com.example.lease_cal.dto.PartyRequestDTO partyRequestDTO, Lead lead) {
        Party party = new Party();
        party.setLead(lead);
        party.setPartyType(partyRequestDTO.getPartyType());
        party.setPersonalName(partyRequestDTO.getPersonalName());
        party.setEmail(partyRequestDTO.getEmail());
        party.setMobileNumber(partyRequestDTO.getMobileNumber());
        party.setDateOfBirth(partyRequestDTO.getDateOfBirth());
        party.setCivilStatus(partyRequestDTO.getCivilStatus());
        party.setPreferredBranch(partyRequestDTO.getPreferredBranch());
        party.setConsiderCrib(partyRequestDTO.getConsiderCrib());
        party.setConsiderAdvanceAnalysis(partyRequestDTO.getConsiderAdvanceAnalysis());
        party.setCreatedBy(partyRequestDTO.getCreatedBy());
        party.setCreatedDate(LocalDate.now());
        
        // Convert and set identifications
        if (partyRequestDTO.getIdentifications() != null) {
            List<PartyIdentification> identifications = new ArrayList<>();
            for (PartyIdentificationRequestDTO idRequestDTO : partyRequestDTO.getIdentifications()) {
                PartyIdentification identification = new PartyIdentification();
                identification.setParty(party);
                identification.setIdentificationType(idRequestDTO.getIdentificationType());
                identification.setIdentificationNumber(idRequestDTO.getIdentificationNumber());
                identification.setCreatedBy(idRequestDTO.getCreatedBy());
                identification.setCreatedDate(LocalDate.now());
                identifications.add(identification);
            }
            party.setIdentifications(identifications);
        }
        
        // Convert and set addresses
        if (partyRequestDTO.getAddresses() != null) {
            List<PartyAddress> addresses = new ArrayList<>();
            for (PartyAddressRequestDTO addressRequestDTO : partyRequestDTO.getAddresses()) {
                PartyAddress address = new PartyAddress();
                address.setParty(party);
                address.setAddress(addressRequestDTO.getAddress());
                addresses.add(address);
            }
            party.setAddresses(addresses);
        }
        
        // Note: Income sources are not saved here - use saveIncomeSources method separately
        
        return party;
    }
    
    /**
     * Convert Lead entity to LeadDTO
     */
    private LeadDTO convertToLeadDTO(Lead lead) {
        LeadDTO leadDTO = new LeadDTO();
        leadDTO.setCompLeadId(lead.getCompLeadId());
        leadDTO.setLeadName(lead.getLeadName());
        leadDTO.setCreationType(lead.getCreationType());
        leadDTO.setCreatedDate(lead.getCreatedDate());
        leadDTO.setCreatedBy(lead.getCreatedBy());
        leadDTO.setModifiedDate(lead.getModifiedDate());
        leadDTO.setModifiedBy(lead.getModifiedBy());
        
        // Convert parties
        if (lead.getParties() != null) {
            List<PartyDTO> partyDTOs = new ArrayList<>();
            for (Party party : lead.getParties()) {
                partyDTOs.add(convertToPartyDTO(party));
            }
            leadDTO.setParties(partyDTOs);
        }
        
        // Convert related parties
        if (lead.getRelatedParties() != null) {
            List<RelatedPartyDTO> relatedPartyDTOs = new ArrayList<>();
            for (RelatedParty relatedParty : lead.getRelatedParties()) {
                relatedPartyDTOs.add(convertToRelatedPartyDTO(relatedParty));
            }
            leadDTO.setRelatedParties(relatedPartyDTOs);
        }
        
        return leadDTO;
    }
    
    /**
     * Convert Party entity to PartyDTO
     */
    private PartyDTO convertToPartyDTO(Party party) {
        PartyDTO partyDTO = new PartyDTO();
        partyDTO.setCompPartyId(party.getCompPartyId());
        partyDTO.setCompLeadId(party.getLead() != null ? party.getLead().getCompLeadId() : null);
        partyDTO.setPartyType(party.getPartyType());
        partyDTO.setPersonalName(party.getPersonalName());
        partyDTO.setEmail(party.getEmail());
        partyDTO.setMobileNumber(party.getMobileNumber());
        partyDTO.setDateOfBirth(party.getDateOfBirth());
        partyDTO.setCivilStatus(party.getCivilStatus());
        partyDTO.setPreferredBranch(party.getPreferredBranch());
        partyDTO.setConsiderCrib(party.getConsiderCrib());
        partyDTO.setConsiderAdvanceAnalysis(party.getConsiderAdvanceAnalysis());
        partyDTO.setCreatedDate(party.getCreatedDate());
        partyDTO.setCreatedBy(party.getCreatedBy());
        partyDTO.setModifiedDate(party.getModifiedDate());
        partyDTO.setModifiedBy(party.getModifiedBy());
        
        // Convert identifications
        if (party.getIdentifications() != null) {
            List<PartyIdentificationDTO> identificationDTOs = new ArrayList<>();
            for (PartyIdentification identification : party.getIdentifications()) {
                identificationDTOs.add(convertToPartyIdentificationDTO(identification));
            }
            partyDTO.setIdentifications(identificationDTOs);
        }
        
        // Convert addresses
        if (party.getAddresses() != null) {
            List<PartyAddressDTO> addressDTOs = new ArrayList<>();
            for (PartyAddress address : party.getAddresses()) {
                addressDTOs.add(convertToPartyAddressDTO(address));
            }
            partyDTO.setAddresses(addressDTOs);
        }
        
        // Convert income sources
        if (party.getIncomeSources() != null) {
            List<IncomeSourceDTO> incomeSourceDTOs = new ArrayList<>();
            for (IncomeSource incomeSource : party.getIncomeSources()) {
                incomeSourceDTOs.add(convertToIncomeSourceDTO(incomeSource));
            }
            partyDTO.setIncomeSources(incomeSourceDTOs);
        }
        
        return partyDTO;
    }
    
    /**
     * Convert PartyIdentification entity to PartyIdentificationDTO
     */
    private PartyIdentificationDTO convertToPartyIdentificationDTO(PartyIdentification identification) {
        PartyIdentificationDTO dto = new PartyIdentificationDTO();
        dto.setIdentificationId(identification.getIdentificationId());
        dto.setCompPartyId(identification.getParty() != null ? identification.getParty().getCompPartyId() : null);
        dto.setIdentificationType(identification.getIdentificationType());
        dto.setIdentificationNumber(identification.getIdentificationNumber());
        dto.setCreatedDate(identification.getCreatedDate());
        dto.setCreatedBy(identification.getCreatedBy());
        dto.setModifiedDate(identification.getModifiedDate());
        dto.setModifiedBy(identification.getModifiedBy());
        return dto;
    }
    
    /**
     * Convert PartyAddress entity to PartyAddressDTO
     */
    private PartyAddressDTO convertToPartyAddressDTO(PartyAddress address) {
        PartyAddressDTO dto = new PartyAddressDTO();
        dto.setAddressesId(address.getAddressesId());
        dto.setCompPartyId(address.getParty() != null ? address.getParty().getCompPartyId() : null);
        dto.setAddress(address.getAddress());
        return dto;
    }
    
    /**
     * Convert IncomeSource entity to IncomeSourceDTO
     */
    private IncomeSourceDTO convertToIncomeSourceDTO(IncomeSource incomeSource) {
        IncomeSourceDTO dto = new IncomeSourceDTO();
        dto.setIncomeSourceId(incomeSource.getIncomeSourceId());
        dto.setCompPartyId(incomeSource.getParty() != null ? incomeSource.getParty().getCompPartyId() : null);
        dto.setIncomeType(incomeSource.getIncomeType());
        dto.setConsiderForRepayment(incomeSource.getConsiderForRepayment());
        return dto;
    }
    
    /**
     * Convert RelatedParty entity to RelatedPartyDTO
     */
    private RelatedPartyDTO convertToRelatedPartyDTO(RelatedParty relatedParty) {
        RelatedPartyDTO dto = new RelatedPartyDTO();
        dto.setRelatedPartyId(relatedParty.getRelatedPartyId());
        dto.setCompLeadId(relatedParty.getLead() != null ? relatedParty.getLead().getCompLeadId() : null);
        dto.setMainPartnerId(relatedParty.getMainParty() != null ? relatedParty.getMainParty().getCompPartyId() : null);
        dto.setRelatedPartnerId(relatedParty.getRelatedParty() != null ? relatedParty.getRelatedParty().getCompPartyId() : null);
        dto.setRelationshipDescription(relatedParty.getRelationshipDescription());
        dto.setConsiderCrib(relatedParty.getConsiderCrib());
        dto.setConsiderAdvanceAnalysis(relatedParty.getConsiderAdvanceAnalysis());
        return dto;
    }
    
    /**
     * Get lead by ID with all child entities
     */
    public LeadDTO getLeadById(Long leadId) {
        Lead lead = leadRepository.findById(leadId)
                .orElseThrow(() -> new RuntimeException("Lead not found with id: " + leadId));
        return convertToLeadDTO(lead);
    }
}

