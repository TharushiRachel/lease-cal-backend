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
    
    @Autowired
    private ComprehensiveFacilityRepository comprehensiveFacilityRepository;
    
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
     * Save or update a comprehensive lead based on compLeadId in the request body
     * If compLeadId is 0 or null, it creates a new lead
     * If compLeadId is provided, it updates the existing lead
     * 
     * @param comprehensiveLeadRequestDTO The comprehensive lead request DTO containing lead and party data
     * @return ComprehensiveLeadDTO with saved data including IDs
     */
    public ComprehensiveLeadDTO saveOrUpdateLeadWithChildren(ComprehensiveLeadRequestDTO comprehensiveLeadRequestDTO) {
        Long leadId = comprehensiveLeadRequestDTO.getCompLeadId();
        if (leadId != null && leadId > 0) {
            return updateLeadWithChildren(leadId, comprehensiveLeadRequestDTO);
        }
        return saveLeadWithChildren(comprehensiveLeadRequestDTO);
    }
    
    /**
     * Save a party for a comprehensive lead
     * 
     * @param compLeadId The ID of the comprehensive lead
     * @param partyRequestDTO The party request DTO containing party data
     * @return PartyDTO with saved data including ID
     */
    public PartyDTO savePartyForLead(Long compLeadId, PartyRequestDTO partyRequestDTO) {
        // Find the comprehensive lead
        ComprehensiveLead comprehensiveLead = comprehensiveLeadRepository.findById(compLeadId)
                .orElseThrow(() -> new RuntimeException("Comprehensive Lead not found with id: " + compLeadId));
        
        // Convert and save party with identifications and addresses
        Party party = convertToPartyEntity(partyRequestDTO, comprehensiveLead);
        
        // Set creationType on the lead if provided in party
        if (partyRequestDTO.getCreationType() != null && comprehensiveLead.getCreationType() == null) {
            comprehensiveLead.setCreationType(partyRequestDTO.getCreationType());
            comprehensiveLeadRepository.save(comprehensiveLead);
        }
        
        // Save party (cascade will save identifications and addresses)
        party = partyRepository.save(party);
        
        // Convert to DTO and return
        return convertToPartyDTO(party);
    }
    
    /**
     * Update a party for a comprehensive lead
     * 
     * @param compLeadId The ID of the comprehensive lead
     * @param partyId The ID of the party to update
     * @param partyRequestDTO The party request DTO containing updated party data
     * @return PartyDTO with updated data
     */
    public PartyDTO updatePartyForLead(Long compLeadId, Long partyId, PartyRequestDTO partyRequestDTO) {
        // Find the comprehensive lead
        ComprehensiveLead comprehensiveLead = comprehensiveLeadRepository.findById(compLeadId)
                .orElseThrow(() -> new RuntimeException("Comprehensive Lead not found with id: " + compLeadId));
        
        // Find the existing party
        Party existingParty = partyRepository.findById(partyId)
                .orElseThrow(() -> new RuntimeException("Party not found with id: " + partyId));
        
        if (existingParty.getLead() == null || !existingParty.getLead().getCompLeadId().equals(compLeadId)) {
            throw new RuntimeException("Party does not belong to lead id: " + compLeadId);
        }
        
        existingParty.setPartyType(partyRequestDTO.getPartyType());
        existingParty.setPersonalName(partyRequestDTO.getPersonalName());
        existingParty.setEmail(partyRequestDTO.getEmail());
        existingParty.setMobileNumber(partyRequestDTO.getMobileNumber());
        existingParty.setDateOfBirth(partyRequestDTO.getDateOfBirth());
        existingParty.setCivilStatus(partyRequestDTO.getCivilStatus());
        existingParty.setPreferredBranch(partyRequestDTO.getPreferredBranch());
        existingParty.setAccountNumber(partyRequestDTO.getAccountNumber());
        if (partyRequestDTO.getConsiderCrib() != null) {
            existingParty.setConsiderCrib(partyRequestDTO.getConsiderCrib() ? "Y" : "N");
        } else {
            existingParty.setConsiderCrib(null);
        }
        if (partyRequestDTO.getConsiderAA() != null) {
            existingParty.setConsiderAdvanceAnalysis(partyRequestDTO.getConsiderAA() ? "Y" : "N");
        } else {
            existingParty.setConsiderAdvanceAnalysis(null);
        }
        existingParty.setModifiedBy(partyRequestDTO.getCreatedBy());
        existingParty.setModifiedDate(LocalDate.now());
        
        // Set creationType on the lead if provided in party
        if (partyRequestDTO.getCreationType() != null && comprehensiveLead.getCreationType() == null) {
            comprehensiveLead.setCreationType(partyRequestDTO.getCreationType());
            comprehensiveLeadRepository.save(comprehensiveLead);
        }
        
        // Replace identifications
        existingParty.getIdentifications().clear();
        if (partyRequestDTO.getIdentifications() != null) {
            for (PartyIdentificationRequestDTO idRequestDTO : partyRequestDTO.getIdentifications()) {
                PartyIdentification identification = new PartyIdentification();
                identification.setParty(existingParty);
                identification.setIdentificationType(idRequestDTO.getIdentificationType());
                identification.setIdentificationNumber(idRequestDTO.getIdentificationNumber());
                identification.setCreatedBy(idRequestDTO.getCreatedBy());
                identification.setCreatedDate(LocalDate.now());
                existingParty.getIdentifications().add(identification);
            }
        }
        
        // Replace addresses
        existingParty.getAddresses().clear();
        if (partyRequestDTO.getAddresses() != null) {
            for (PartyAddressRequestDTO addressRequestDTO : partyRequestDTO.getAddresses()) {
                PartyAddress address = new PartyAddress();
                address.setParty(existingParty);
                address.setAddress(addressRequestDTO.getAddress());
                existingParty.getAddresses().add(address);
            }
        }
        
        Party savedParty = partyRepository.save(existingParty);
        return convertToPartyDTO(savedParty);
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
            incomeSource.setPartyName(incomeRequestDTO.getParty());
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
     * Update income sources for a party by deleting existing records and recreating them
     * Uses the same saveIncomeSources method after deleting existing records for the specific party ID
     * 
     * @param partyId The ID of the party
     * @param incomeSourceRequestDTOs List of income source request DTOs
     * @return List of saved IncomeSourceDTOs
     * @throws RuntimeException if party is not found
     */
    public List<IncomeSourceDTO> updateIncomeSources(Long partyId, List<IncomeSourceRequestDTO> incomeSourceRequestDTOs) {
        // Verify the party exists
        Party party = partyRepository.findById(partyId)
                .orElseThrow(() -> new RuntimeException("Party not found with id: " + partyId));
        
        // Find and delete all existing income sources for this party
        List<IncomeSource> existingIncomeSources = incomeSourceRepository.findByPartyId(partyId);
        if (existingIncomeSources != null && !existingIncomeSources.isEmpty()) {
            incomeSourceRepository.deleteAll(existingIncomeSources);
        }
        
        // Use existing save method to recreate income sources
        return saveIncomeSources(partyId, incomeSourceRequestDTOs);
    }
    
    /**
     * Save or update income sources based on partyId in the request body
     * This replaces any existing income sources for the party
     * 
     * @param incomeSourceListRequestDTO The income source list request DTO with partyId
     * @return List of saved IncomeSourceDTOs
     * @throws RuntimeException if party is not found
     */
    public List<IncomeSourceDTO> saveOrUpdateIncomeSources(IncomeSourceListRequestDTO incomeSourceListRequestDTO) {
        Long partyId = incomeSourceListRequestDTO.getPartyId();
        if (partyId == null || partyId <= 0) {
            throw new RuntimeException("Party ID is required");
        }
        
        List<IncomeSourceRequestDTO> incomeSources = incomeSourceListRequestDTO.getIncomeSources();
        if (incomeSources == null) {
            incomeSources = new ArrayList<>();
        }
        
        return updateIncomeSources(partyId, incomeSources);
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
     * Update related parties for a lead by deleting existing records and recreating them
     * Uses the same saveRelatedParties method after deleting existing records for the specific lead ID
     * 
     * @param leadId The ID of the lead
     * @param relatedPartyRequestDTOs List of related party request DTOs
     * @return List of saved RelatedPartyDTOs
     * @throws RuntimeException if comprehensive lead is not found
     */
    public List<RelatedPartyDTO> updateRelatedParties(Long leadId, List<RelatedPartyRequestDTO> relatedPartyRequestDTOs) {
        // Verify the comprehensive lead exists
        ComprehensiveLead comprehensiveLead = comprehensiveLeadRepository.findById(leadId)
                .orElseThrow(() -> new RuntimeException("Comprehensive Lead not found with id: " + leadId));
        
        // Find and delete all existing related parties for this lead
        List<RelatedParty> existingRelatedParties = relatedPartyRepository.findByLeadId(leadId);
        if (existingRelatedParties != null && !existingRelatedParties.isEmpty()) {
            relatedPartyRepository.deleteAll(existingRelatedParties);
        }
        
        // Use existing save method to recreate related parties
        return saveRelatedParties(leadId, relatedPartyRequestDTOs);
    }
    
    /**
     * Save or update related parties based on leadId in the request body
     * This replaces any existing related parties for the lead
     * 
     * @param relatedPartyListRequestDTO The related party list request DTO with leadId
     * @return List of saved RelatedPartyDTOs
     * @throws RuntimeException if comprehensive lead is not found
     */
    public List<RelatedPartyDTO> saveOrUpdateRelatedParties(RelatedPartyListRequestDTO relatedPartyListRequestDTO) {
        Long leadId = relatedPartyListRequestDTO.getLeadId();
        if (leadId == null || leadId <= 0) {
            throw new RuntimeException("Lead ID is required");
        }
        
        List<RelatedPartyRequestDTO> relatedParties = relatedPartyListRequestDTO.getRelatedParties();
        if (relatedParties == null) {
            relatedParties = new ArrayList<>();
        }
        
        return updateRelatedParties(leadId, relatedParties);
    }
    
    /**
     * Convert RelatedPartyRequestDTO to RelatedParty entity
     * Note: This method expects that parties are already saved and have IDs
     */
    private RelatedParty convertToRelatedPartyEntity(RelatedPartyRequestDTO relatedPartyRequestDTO, ComprehensiveLead comprehensiveLead) {
        RelatedParty relatedParty = new RelatedParty();
        relatedParty.setLead(comprehensiveLead);
        relatedParty.setRelationshipDescription(relatedPartyRequestDTO.getRelationshipDescription());
        relatedParty.setIsRelationShip(relatedPartyRequestDTO.getIsRelationShip());
        relatedParty.setSharePresentage(relatedPartyRequestDTO.getSharePresentage());
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
        party.setAccountNumber(partyRequestDTO.getAccountNumber());
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
        partyDTO.setAccountNumber(party.getAccountNumber());
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
        dto.setParty(incomeSource.getPartyName());
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
        dto.setIsRelationShip(relatedParty.getIsRelationShip());
        dto.setCompLeadId(relatedParty.getLead() != null ? relatedParty.getLead().getCompLeadId() : null);
        dto.setMainPartnerId(relatedParty.getMainParty() != null ? relatedParty.getMainParty().getCompPartyId() : null);
        dto.setRelatedPartnerId(relatedParty.getRelatedParty() != null ? relatedParty.getRelatedParty().getCompPartyId() : null);
        dto.setRelationshipDescription(relatedParty.getRelationshipDescription());
        dto.setSharePresentage(relatedParty.getSharePresentage());
        dto.setConsiderCrib(relatedParty.getConsiderCrib());
        dto.setConsiderAdvanceAnalysis(relatedParty.getConsiderAdvanceAnalysis());
        return dto;
    }
    
    /**
     * Get comprehensive lead by ID with all child entities
     * 
     * @param leadId The ID of the comprehensive lead to retrieve
     * @return ComprehensiveLeadDTO with all nested entities (parties, identifications, addresses, income sources, related parties)
     * @throws RuntimeException if comprehensive lead is not found
     */
    public ComprehensiveLeadDTO getComprehensiveLeadById(Long leadId) {
        ComprehensiveLead comprehensiveLead = comprehensiveLeadRepository.findByIdWithAllRelations(leadId)
                .orElseThrow(() -> new RuntimeException("Comprehensive Lead not found with id: " + leadId));
        return convertToComprehensiveLeadDTO(comprehensiveLead);
    }
    
    /**
     * Update a comprehensive lead by deleting existing child records and recreating them
     * Uses the same child methods (savePartyForLead, saveIncomeSources, saveRelatedParties) 
     * after deleting existing records for the specific ID
     * 
     * @param leadId The ID of the comprehensive lead to update
     * @param comprehensiveLeadRequestDTO The comprehensive lead request DTO containing updated lead and party data
     * @return ComprehensiveLeadDTO with updated data including IDs
     * @throws RuntimeException if comprehensive lead is not found
     */
    public ComprehensiveLeadDTO updateLeadWithChildren(Long leadId, ComprehensiveLeadRequestDTO comprehensiveLeadRequestDTO) {
        // Find the existing comprehensive lead with all relations
        ComprehensiveLead comprehensiveLead = comprehensiveLeadRepository.findByIdWithAllRelations(leadId)
                .orElseThrow(() -> new RuntimeException("Comprehensive Lead not found with id: " + leadId));
        
        // Delete all existing related parties first (they reference parties, so delete before parties)
        if (comprehensiveLead.getRelatedParties() != null && !comprehensiveLead.getRelatedParties().isEmpty()) {
            relatedPartyRepository.deleteAll(comprehensiveLead.getRelatedParties());
            comprehensiveLead.getRelatedParties().clear();
        }
        
        // Delete all existing parties (cascade will delete identifications, addresses, and income sources)
        if (comprehensiveLead.getParties() != null && !comprehensiveLead.getParties().isEmpty()) {
            partyRepository.deleteAll(comprehensiveLead.getParties());
            comprehensiveLead.getParties().clear();
        }
        
        // Update the lead's basic fields
        if (comprehensiveLeadRequestDTO.getLeadName() != null) {
            comprehensiveLead.setLeadName(comprehensiveLeadRequestDTO.getLeadName());
        }
        if (comprehensiveLeadRequestDTO.getCreatedBy() != null) {
            comprehensiveLead.setModifiedBy(comprehensiveLeadRequestDTO.getCreatedBy());
        }
        comprehensiveLead.setModifiedDate(LocalDate.now());
        
        // Save the updated lead
        comprehensiveLead = comprehensiveLeadRepository.save(comprehensiveLead);
        
        // Recreate parties using existing method
        if (comprehensiveLeadRequestDTO.getParties() != null && !comprehensiveLeadRequestDTO.getParties().isEmpty()) {
            for (PartyRequestDTO partyRequestDTO : comprehensiveLeadRequestDTO.getParties()) {
                // Save party with identifications and addresses
                PartyDTO savedParty = savePartyForLead(leadId, partyRequestDTO);
                
                // Save income sources for this party if provided
                if (partyRequestDTO.getIncomeSources() != null && !partyRequestDTO.getIncomeSources().isEmpty()) {
                    saveIncomeSources(savedParty.getCompPartyId(), partyRequestDTO.getIncomeSources());
                }
            }
        }
        
        // Recreate related parties using existing method
        if (comprehensiveLeadRequestDTO.getRelatedParties() != null && !comprehensiveLeadRequestDTO.getRelatedParties().isEmpty()) {
            saveRelatedParties(leadId, comprehensiveLeadRequestDTO.getRelatedParties());
        }
        
        // Fetch the updated lead with all relations and return
        comprehensiveLead = comprehensiveLeadRepository.findByIdWithAllRelations(leadId)
                .orElseThrow(() -> new RuntimeException("Comprehensive Lead not found with id: " + leadId));
        return convertToComprehensiveLeadDTO(comprehensiveLead);
    }
    
    /**
     * Save or update a facility based on compFacilityId in the request body
     * If compFacilityId is 0 or null, it creates a new facility using compLeadId
     * If compFacilityId is provided, it updates the existing facility
     * 
     * @param facilityDTO The facility DTO containing facility data
     * @return ComprehensiveFacilityDTO with saved data including ID
     * @throws RuntimeException if required entities are not found
     */
    public ComprehensiveFacilityDTO saveOrUpdateFacility(ComprehensiveFacilityDTO facilityDTO) {
        Long facilityId = facilityDTO.getCompFacilityId();
        
        if (facilityId != null && facilityId > 0) {
            // Update existing facility
            ComprehensiveFacility existingFacility = comprehensiveFacilityRepository.findById(facilityId)
                    .orElseThrow(() -> new RuntimeException("Facility not found with id: " + facilityId));
            
            existingFacility.setFacilityDescription(facilityDTO.getFacilityDescription());
            existingFacility.setRequestedTenure(facilityDTO.getRequestedTenure());
            existingFacility.setLeaseRental(facilityDTO.getLeaseRental());
            existingFacility.setProcessingFee(facilityDTO.getProcessingFee());
            existingFacility.setValidityOfOffer(facilityDTO.getValidityOfOffer());
            existingFacility.setLeaseAmount(facilityDTO.getLeaseAmount());
            existingFacility.setRepaymentMode(facilityDTO.getRepaymentMode());
            existingFacility.setUpfront(facilityDTO.getUpfront());
            existingFacility.setInsurance(facilityDTO.getInsurance());
            existingFacility.setModifiedDate(LocalDate.now());
            if (facilityDTO.getModifiedBy() != null) {
                existingFacility.setModifiedBy(facilityDTO.getModifiedBy());
            } else if (facilityDTO.getCreatedBy() != null) {
                existingFacility.setModifiedBy(facilityDTO.getCreatedBy());
            }
            
            ComprehensiveFacility savedFacility = comprehensiveFacilityRepository.save(existingFacility);
            return convertToFacilityDTO(savedFacility);
        }
        
        // Create new facility
        Long leadId = facilityDTO.getCompLeadId();
        if (leadId == null || leadId <= 0) {
            throw new RuntimeException("Comprehensive Lead ID is required to create a facility");
        }
        
        ComprehensiveLead comprehensiveLead = comprehensiveLeadRepository.findById(leadId)
                .orElseThrow(() -> new RuntimeException("Comprehensive Lead not found with id: " + leadId));
        
        ComprehensiveFacility facility = convertToFacilityEntity(facilityDTO, comprehensiveLead);
        facility = comprehensiveFacilityRepository.save(facility);
        return convertToFacilityDTO(facility);
    }
    
    /**
     * Convert ComprehensiveFacilityDTO to ComprehensiveFacility entity
     */
    private ComprehensiveFacility convertToFacilityEntity(ComprehensiveFacilityDTO facilityDTO, ComprehensiveLead comprehensiveLead) {
        ComprehensiveFacility facility = new ComprehensiveFacility();
        facility.setLead(comprehensiveLead);
        facility.setFacilityDescription(facilityDTO.getFacilityDescription());
        facility.setRequestedTenure(facilityDTO.getRequestedTenure());
        facility.setLeaseRental(facilityDTO.getLeaseRental());
        facility.setProcessingFee(facilityDTO.getProcessingFee());
        facility.setValidityOfOffer(facilityDTO.getValidityOfOffer());
        facility.setLeaseAmount(facilityDTO.getLeaseAmount());
        facility.setRepaymentMode(facilityDTO.getRepaymentMode());
        facility.setUpfront(facilityDTO.getUpfront());
        facility.setInsurance(facilityDTO.getInsurance());
        facility.setCreatedBy(facilityDTO.getCreatedBy());
        facility.setCreatedDate(LocalDate.now());
        
        return facility;
    }
    
    /**
     * Convert ComprehensiveFacility entity to ComprehensiveFacilityDTO
     */
    private ComprehensiveFacilityDTO convertToFacilityDTO(ComprehensiveFacility facility) {
        ComprehensiveFacilityDTO dto = new ComprehensiveFacilityDTO();
        dto.setCompFacilityId(facility.getCompFacilityId());
        dto.setCompLeadId(facility.getLead() != null ? facility.getLead().getCompLeadId() : null);
        dto.setFacilityDescription(facility.getFacilityDescription());
        dto.setRequestedTenure(facility.getRequestedTenure());
        dto.setLeaseRental(facility.getLeaseRental());
        dto.setProcessingFee(facility.getProcessingFee());
        dto.setValidityOfOffer(facility.getValidityOfOffer());
        dto.setLeaseAmount(facility.getLeaseAmount());
        dto.setRepaymentMode(facility.getRepaymentMode());
        dto.setUpfront(facility.getUpfront());
        dto.setInsurance(facility.getInsurance());
        dto.setCreatedDate(facility.getCreatedDate());
        dto.setCreatedBy(facility.getCreatedBy());
        dto.setModifiedDate(facility.getModifiedDate());
        dto.setModifiedBy(facility.getModifiedBy());
        
        return dto;
    }
}
