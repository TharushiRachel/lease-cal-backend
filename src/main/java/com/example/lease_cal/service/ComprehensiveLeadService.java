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
public class ComprehensiveLeadService {
    
    @Autowired
    private ComprehensiveLeadRepository comprehensiveLeadRepository;
    
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
     * @param comprehensiveLeadRequestDTO The comprehensive lead request DTO containing lead and party data
     * @return ComprehensiveLeadDTO with saved data including IDs
     */
    public ComprehensiveLeadDTO saveLeadWithChildren(ComprehensiveLeadRequestDTO comprehensiveLeadRequestDTO) {
        // Create and save ComprehensiveLead entity
        ComprehensiveLead comprehensiveLead = new ComprehensiveLead();
        comprehensiveLead.setLeadName(comprehensiveLeadRequestDTO.getLeadName());
        // creationType is now at party level, not lead level
        comprehensiveLead.setCreatedBy(comprehensiveLeadRequestDTO.getCreatedBy());
        comprehensiveLead.setCreatedDate(LocalDate.now());
        
        // Save comprehensive lead first to get the ID
        comprehensiveLead = comprehensiveLeadRepository.save(comprehensiveLead);
        
        // Convert and save parties with identifications and addresses only
        if (comprehensiveLeadRequestDTO.getParties() != null && !comprehensiveLeadRequestDTO.getParties().isEmpty()) {
            List<Party> parties = new ArrayList<>();
            for (PartyRequestDTO partyRequestDTO : comprehensiveLeadRequestDTO.getParties()) {
                Party party = convertToPartyEntity(partyRequestDTO, comprehensiveLead);
                parties.add(party);
            }
            comprehensiveLead.setParties(parties);
        }
        
        // Save comprehensive lead again with parties (cascade will save identifications and addresses)
        comprehensiveLead = comprehensiveLeadRepository.save(comprehensiveLead);
        
        // Convert to DTO and return
        return convertToComprehensiveLeadDTO(comprehensiveLead);
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
        // Find the comprehensive lead
        ComprehensiveLead comprehensiveLead = comprehensiveLeadRepository.findById(leadId)
                .orElseThrow(() -> new RuntimeException("Comprehensive Lead not found with id: " + leadId));
        
        // Convert and save related parties
        List<RelatedParty> relatedParties = new ArrayList<>();
        for (RelatedPartyRequestDTO relatedPartyRequestDTO : relatedPartyRequestDTOs) {
            RelatedParty relatedParty = convertToRelatedPartyEntity(relatedPartyRequestDTO, comprehensiveLead);
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
    private RelatedParty convertToRelatedPartyEntity(RelatedPartyRequestDTO relatedPartyRequestDTO, ComprehensiveLead comprehensiveLead) {
        RelatedParty relatedParty = new RelatedParty();
        relatedParty.setLead(comprehensiveLead);
        relatedParty.setRelationshipDescription(relatedPartyRequestDTO.getRelationshipDescription());
        // Note: isRelationShip and sharePresentage are not yet in the entity/database
        // They are accepted in the DTO but won't be persisted until added to the schema
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
    private Party convertToPartyEntity(PartyRequestDTO partyRequestDTO, ComprehensiveLead comprehensiveLead) {
        Party party = new Party();
        party.setLead(comprehensiveLead);
        party.setPartyType(partyRequestDTO.getPartyType());
        party.setPersonalName(partyRequestDTO.getPersonalName());
        party.setEmail(partyRequestDTO.getEmail());
        party.setMobileNumber(partyRequestDTO.getMobileNumber());
        party.setDateOfBirth(partyRequestDTO.getDateOfBirth());
        party.setCivilStatus(partyRequestDTO.getCivilStatus());
        party.setPreferredBranch(partyRequestDTO.getPreferredBranch());
        // Convert Boolean to String for considerCrib and considerAA
        if (partyRequestDTO.getConsiderCrib() != null) {
            party.setConsiderCrib(partyRequestDTO.getConsiderCrib() ? "Y" : "N");
        }
        if (partyRequestDTO.getConsiderAA() != null) {
            party.setConsiderAdvanceAnalysis(partyRequestDTO.getConsiderAA() ? "Y" : "N");
        }
        party.setCreatedBy(partyRequestDTO.getCreatedBy());
        party.setCreatedDate(LocalDate.now());
        
        // Set creationType on the lead if provided in party (use first party's creationType)
        if (partyRequestDTO.getCreationType() != null && comprehensiveLead.getCreationType() == null) {
            comprehensiveLead.setCreationType(partyRequestDTO.getCreationType());
        }
        
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
     * Convert ComprehensiveLead entity to ComprehensiveLeadDTO
     */
    private ComprehensiveLeadDTO convertToComprehensiveLeadDTO(ComprehensiveLead comprehensiveLead) {
        ComprehensiveLeadDTO comprehensiveLeadDTO = new ComprehensiveLeadDTO();
        comprehensiveLeadDTO.setCompLeadId(comprehensiveLead.getCompLeadId());
        comprehensiveLeadDTO.setLeadName(comprehensiveLead.getLeadName());
        comprehensiveLeadDTO.setCreationType(comprehensiveLead.getCreationType());
        comprehensiveLeadDTO.setCreatedDate(comprehensiveLead.getCreatedDate());
        comprehensiveLeadDTO.setCreatedBy(comprehensiveLead.getCreatedBy());
        comprehensiveLeadDTO.setModifiedDate(comprehensiveLead.getModifiedDate());
        comprehensiveLeadDTO.setModifiedBy(comprehensiveLead.getModifiedBy());
        
        // Convert parties
        if (comprehensiveLead.getParties() != null) {
            List<PartyDTO> partyDTOs = new ArrayList<>();
            for (Party party : comprehensiveLead.getParties()) {
                partyDTOs.add(convertToPartyDTO(party));
            }
            comprehensiveLeadDTO.setParties(partyDTOs);
        }
        
        // Convert related parties
        if (comprehensiveLead.getRelatedParties() != null) {
            List<RelatedPartyDTO> relatedPartyDTOs = new ArrayList<>();
            for (RelatedParty relatedParty : comprehensiveLead.getRelatedParties()) {
                relatedPartyDTOs.add(convertToRelatedPartyDTO(relatedParty));
            }
            comprehensiveLeadDTO.setRelatedParties(relatedPartyDTOs);
        }
        
        return comprehensiveLeadDTO;
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
        // Note: isRelationShip and sharePresentage are not yet in the entity
        // They will be null in the response until added to the entity/database
        dto.setCompLeadId(relatedParty.getLead() != null ? relatedParty.getLead().getCompLeadId() : null);
        dto.setMainPartnerId(relatedParty.getMainParty() != null ? relatedParty.getMainParty().getCompPartyId() : null);
        dto.setRelatedPartnerId(relatedParty.getRelatedParty() != null ? relatedParty.getRelatedParty().getCompPartyId() : null);
        dto.setRelationshipDescription(relatedParty.getRelationshipDescription());
        dto.setConsiderCrib(relatedParty.getConsiderCrib());
        dto.setConsiderAdvanceAnalysis(relatedParty.getConsiderAdvanceAnalysis());
        return dto;
    }
    
    /**
     * Get comprehensive lead by ID with all child entities
     */
    public ComprehensiveLeadDTO getComprehensiveLeadById(Long leadId) {
        ComprehensiveLead comprehensiveLead = comprehensiveLeadRepository.findById(leadId)
                .orElseThrow(() -> new RuntimeException("Comprehensive Lead not found with id: " + leadId));
        return convertToComprehensiveLeadDTO(comprehensiveLead);
    }
}
