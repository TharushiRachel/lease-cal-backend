package com.example.lease_cal.service;

import com.example.lease_cal.dto.LeadDTO;
import com.example.lease_cal.dto.LeadRequestDTO;
import com.example.lease_cal.entity.*;
import com.example.lease_cal.repository.LeadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class LeadService {
    
    @Autowired
    private LeadRepository leadRepository;
    
    /**
     * Save a lead with all its child entities (parties, identifications, addresses, income sources, related parties)
     * 
     * @param leadRequestDTO The lead request DTO containing all data
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
        
        // Convert and save parties with their child entities
        if (leadRequestDTO.getParties() != null && !leadRequestDTO.getParties().isEmpty()) {
            List<Party> parties = new ArrayList<>();
            for (com.example.lease_cal.dto.PartyRequestDTO partyRequestDTO : leadRequestDTO.getParties()) {
                Party party = convertToPartyEntity(partyRequestDTO, lead);
                parties.add(party);
            }
            lead.setParties(parties);
        }
        
        // Save lead again with parties (cascade will save children)
        lead = leadRepository.save(lead);
        
        // Save related parties if provided
        // Note: Related parties reference parties by ID, so parties must be saved first
        if (leadRequestDTO.getRelatedParties() != null && !leadRequestDTO.getRelatedParties().isEmpty()) {
            List<RelatedParty> relatedParties = new ArrayList<>();
            for (com.example.lease_cal.dto.RelatedPartyRequestDTO relatedPartyRequestDTO : leadRequestDTO.getRelatedParties()) {
                RelatedParty relatedParty = convertToRelatedPartyEntity(relatedPartyRequestDTO, lead);
                relatedParties.add(relatedParty);
            }
            lead.setRelatedParties(relatedParties);
            lead = leadRepository.save(lead);
        }
        
        // Convert to DTO and return
        return convertToLeadDTO(lead);
    }
    
    /**
     * Convert RelatedPartyRequestDTO to RelatedParty entity
     * Note: This method expects that parties are already saved and have IDs
     */
    private RelatedParty convertToRelatedPartyEntity(com.example.lease_cal.dto.RelatedPartyRequestDTO relatedPartyRequestDTO, Lead lead) {
        RelatedParty relatedParty = new RelatedParty();
        relatedParty.setLead(lead);
        relatedParty.setRelationshipDescription(relatedPartyRequestDTO.getRelationshipDescription());
        relatedParty.setConsiderCrib(relatedPartyRequestDTO.getConsiderCrib());
        relatedParty.setConsiderAdvanceAnalysis(relatedPartyRequestDTO.getConsiderAdvanceAnalysis());
        
        // Find main party and related party by their IDs from the saved lead
        if (relatedPartyRequestDTO.getMainPartnerId() != null) {
            Party mainParty = lead.getParties().stream()
                    .filter(p -> p.getCompPartyId() != null && p.getCompPartyId().equals(relatedPartyRequestDTO.getMainPartnerId()))
                    .findFirst()
                    .orElseThrow(() -> new RuntimeException("Main party not found with id: " + relatedPartyRequestDTO.getMainPartnerId()));
            relatedParty.setMainParty(mainParty);
        }
        
        if (relatedPartyRequestDTO.getRelatedPartnerId() != null) {
            Party relatedPartyEntity = lead.getParties().stream()
                    .filter(p -> p.getCompPartyId() != null && p.getCompPartyId().equals(relatedPartyRequestDTO.getRelatedPartnerId()))
                    .findFirst()
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
        party.setCreatedBy(partyRequestDTO.getCreatedBy());
        party.setCreatedDate(LocalDate.now());
        
        // Convert and set identifications
        if (partyRequestDTO.getIdentifications() != null) {
            List<PartyIdentification> identifications = new ArrayList<>();
            for (com.example.lease_cal.dto.PartyIdentificationRequestDTO idRequestDTO : partyRequestDTO.getIdentifications()) {
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
            for (com.example.lease_cal.dto.PartyAddressRequestDTO addressRequestDTO : partyRequestDTO.getAddresses()) {
                PartyAddress address = new PartyAddress();
                address.setParty(party);
                address.setAddress(addressRequestDTO.getAddress());
                addresses.add(address);
            }
            party.setAddresses(addresses);
        }
        
        // Convert and set income sources
        if (partyRequestDTO.getIncomeSources() != null) {
            List<IncomeSource> incomeSources = new ArrayList<>();
            for (com.example.lease_cal.dto.IncomeSourceRequestDTO incomeRequestDTO : partyRequestDTO.getIncomeSources()) {
                IncomeSource incomeSource = new IncomeSource();
                incomeSource.setParty(party);
                incomeSource.setIncomeType(incomeRequestDTO.getIncomeType());
                incomeSource.setConsiderForRepayment(incomeRequestDTO.getConsiderForRepayment());
                incomeSources.add(incomeSource);
            }
            party.setIncomeSources(incomeSources);
        }
        
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
            List<com.example.lease_cal.dto.PartyDTO> partyDTOs = new ArrayList<>();
            for (Party party : lead.getParties()) {
                partyDTOs.add(convertToPartyDTO(party));
            }
            leadDTO.setParties(partyDTOs);
        }
        
        // Convert related parties
        if (lead.getRelatedParties() != null) {
            List<com.example.lease_cal.dto.RelatedPartyDTO> relatedPartyDTOs = new ArrayList<>();
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
    private com.example.lease_cal.dto.PartyDTO convertToPartyDTO(Party party) {
        com.example.lease_cal.dto.PartyDTO partyDTO = new com.example.lease_cal.dto.PartyDTO();
        partyDTO.setCompPartyId(party.getCompPartyId());
        partyDTO.setCompLeadId(party.getLead() != null ? party.getLead().getCompLeadId() : null);
        partyDTO.setPartyType(party.getPartyType());
        partyDTO.setPersonalName(party.getPersonalName());
        partyDTO.setEmail(party.getEmail());
        partyDTO.setMobileNumber(party.getMobileNumber());
        partyDTO.setDateOfBirth(party.getDateOfBirth());
        partyDTO.setCivilStatus(party.getCivilStatus());
        partyDTO.setPreferredBranch(party.getPreferredBranch());
        partyDTO.setCreatedDate(party.getCreatedDate());
        partyDTO.setCreatedBy(party.getCreatedBy());
        partyDTO.setModifiedDate(party.getModifiedDate());
        partyDTO.setModifiedBy(party.getModifiedBy());
        
        // Convert identifications
        if (party.getIdentifications() != null) {
            List<com.example.lease_cal.dto.PartyIdentificationDTO> identificationDTOs = new ArrayList<>();
            for (PartyIdentification identification : party.getIdentifications()) {
                identificationDTOs.add(convertToPartyIdentificationDTO(identification));
            }
            partyDTO.setIdentifications(identificationDTOs);
        }
        
        // Convert addresses
        if (party.getAddresses() != null) {
            List<com.example.lease_cal.dto.PartyAddressDTO> addressDTOs = new ArrayList<>();
            for (PartyAddress address : party.getAddresses()) {
                addressDTOs.add(convertToPartyAddressDTO(address));
            }
            partyDTO.setAddresses(addressDTOs);
        }
        
        // Convert income sources
        if (party.getIncomeSources() != null) {
            List<com.example.lease_cal.dto.IncomeSourceDTO> incomeSourceDTOs = new ArrayList<>();
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
    private com.example.lease_cal.dto.PartyIdentificationDTO convertToPartyIdentificationDTO(PartyIdentification identification) {
        com.example.lease_cal.dto.PartyIdentificationDTO dto = new com.example.lease_cal.dto.PartyIdentificationDTO();
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
    private com.example.lease_cal.dto.PartyAddressDTO convertToPartyAddressDTO(PartyAddress address) {
        com.example.lease_cal.dto.PartyAddressDTO dto = new com.example.lease_cal.dto.PartyAddressDTO();
        dto.setAddressesId(address.getAddressesId());
        dto.setCompPartyId(address.getParty() != null ? address.getParty().getCompPartyId() : null);
        dto.setAddress(address.getAddress());
        return dto;
    }
    
    /**
     * Convert IncomeSource entity to IncomeSourceDTO
     */
    private com.example.lease_cal.dto.IncomeSourceDTO convertToIncomeSourceDTO(IncomeSource incomeSource) {
        com.example.lease_cal.dto.IncomeSourceDTO dto = new com.example.lease_cal.dto.IncomeSourceDTO();
        dto.setIncomeSourceId(incomeSource.getIncomeSourceId());
        dto.setCompPartyId(incomeSource.getParty() != null ? incomeSource.getParty().getCompPartyId() : null);
        dto.setIncomeType(incomeSource.getIncomeType());
        dto.setConsiderForRepayment(incomeSource.getConsiderForRepayment());
        return dto;
    }
    
    /**
     * Convert RelatedParty entity to RelatedPartyDTO
     */
    private com.example.lease_cal.dto.RelatedPartyDTO convertToRelatedPartyDTO(RelatedParty relatedParty) {
        com.example.lease_cal.dto.RelatedPartyDTO dto = new com.example.lease_cal.dto.RelatedPartyDTO();
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

