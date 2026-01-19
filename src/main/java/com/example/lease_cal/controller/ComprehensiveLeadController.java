package com.example.lease_cal.controller;

import com.example.lease_cal.dto.*;
import com.example.lease_cal.service.ComprehensiveLeadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/comprehensive-leads")
@CrossOrigin(origins = "*")
public class ComprehensiveLeadController {
    
    @Autowired
    private ComprehensiveLeadService comprehensiveLeadService;


    
    
    /**
     * Create a new comprehensive lead with parties, identifications, and addresses only
     * 
     * @param comprehensiveLeadRequestDTO The comprehensive lead request DTO containing lead and party data
     * @return ResponseEntity with ComprehensiveLeadDTO and HTTP status
     */
    @PostMapping
    public ResponseEntity<?> saveLeadWithChildren(@RequestBody ComprehensiveLeadRequestDTO comprehensiveLeadRequestDTO) {
        try {
            ComprehensiveLeadDTO savedLead = comprehensiveLeadService.saveOrUpdateLeadWithChildren(comprehensiveLeadRequestDTO);
            HttpStatus status = (comprehensiveLeadRequestDTO.getCompLeadId() != null
                    && comprehensiveLeadRequestDTO.getCompLeadId() > 0)
                    ? HttpStatus.OK
                    : HttpStatus.CREATED;
            return ResponseEntity.status(status).body(savedLead);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ErrorResponse("Validation Error", e.getMessage()));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ErrorResponse("Error saving comprehensive lead", e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ErrorResponse("Unexpected error", e.getMessage()));
        }
    }
    
    /**
     * Save a party for a comprehensive lead
     * 
     * @param leadId The ID of the comprehensive lead
     * @param partyRequestDTO The party request DTO containing party data
     * @return ResponseEntity with PartyDTO and HTTP status
     */
    @PostMapping("/{leadId}/parties")
    public ResponseEntity<?> savePartyForLead(
            @PathVariable Long leadId,
            @RequestBody PartyRequestDTO partyRequestDTO) {
        try {
            PartyDTO savedParty = comprehensiveLeadService.savePartyForLead(leadId, partyRequestDTO);
            HttpStatus status = (partyRequestDTO.getCompPartyId() != null && partyRequestDTO.getCompPartyId() > 0)
                    ? HttpStatus.OK
                    : HttpStatus.CREATED;
            return ResponseEntity.status(status).body(savedParty);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ErrorResponse("Error saving party", e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ErrorResponse("Unexpected error", e.getMessage()));
        }
    }
    
    /**
     * Update a party for a comprehensive lead
     * 
     * @param leadId The ID of the comprehensive lead
     * @param partyId The ID of the party to update
     * @param partyRequestDTO The party request DTO containing updated party data
     * @return ResponseEntity with PartyDTO and HTTP status
     */
    @PutMapping("/{leadId}/parties/{partyId}")
    public ResponseEntity<?> updatePartyForLead(
            @PathVariable Long leadId,
            @PathVariable Long partyId,
            @RequestBody PartyRequestDTO partyRequestDTO) {
        try {
            PartyDTO updatedParty = comprehensiveLeadService.updatePartyForLead(leadId, partyId, partyRequestDTO);
            return ResponseEntity.ok(updatedParty);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ErrorResponse("Error updating party", e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ErrorResponse("Unexpected error", e.getMessage()));
        }
    }
    
    /**
     * Save income sources for a party
     * 
     * @param partyId The ID of the party
     * @param incomeSourceRequestDTOs List of income source request DTOs
     * @return ResponseEntity with list of IncomeSourceDTO and HTTP status
     */
    @PostMapping("/parties/{partyId}/income-sources")
    public ResponseEntity<?> saveIncomeSources(
            @PathVariable Long partyId,
            @RequestBody List<IncomeSourceRequestDTO> incomeSourceRequestDTOs) {
        try {
            IncomeSourceListRequestDTO requestDTO = new IncomeSourceListRequestDTO();
            requestDTO.setPartyId(partyId);
            requestDTO.setIncomeSources(incomeSourceRequestDTOs);
            List<IncomeSourceDTO> savedIncomeSources = comprehensiveLeadService.saveOrUpdateIncomeSources(requestDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedIncomeSources);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ErrorResponse("Error saving income sources", e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ErrorResponse("Unexpected error", e.getMessage()));
        }
    }
    
    /**
     * Save or update income sources using partyId in the request body
     * 
     * @param incomeSourceListRequestDTO The request DTO containing partyId and income sources
     * @return ResponseEntity with list of IncomeSourceDTO and HTTP status
     */
    @PostMapping("/income-sources")
    public ResponseEntity<?> saveOrUpdateIncomeSources(@RequestBody IncomeSourceListRequestDTO incomeSourceListRequestDTO) {
        try {
            List<IncomeSourceDTO> savedIncomeSources = comprehensiveLeadService.saveOrUpdateIncomeSources(
                    incomeSourceListRequestDTO
            );
            return ResponseEntity.ok(savedIncomeSources);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ErrorResponse("Error saving income sources", e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ErrorResponse("Unexpected error", e.getMessage()));
        }
    }
    
    /**
     * Update income sources for a party by deleting existing records and recreating them
     * Uses the same saveIncomeSources method after deleting existing records for the specific party ID
     * 
     * @param partyId The ID of the party
     * @param incomeSourceRequestDTOs List of income source request DTOs
     * @return ResponseEntity with list of IncomeSourceDTO and HTTP status
     */
    @PutMapping("/parties/{partyId}/income-sources")
    public ResponseEntity<?> updateIncomeSources(
            @PathVariable Long partyId,
            @RequestBody List<IncomeSourceRequestDTO> incomeSourceRequestDTOs) {
        try {
            IncomeSourceListRequestDTO requestDTO = new IncomeSourceListRequestDTO();
            requestDTO.setPartyId(partyId);
            requestDTO.setIncomeSources(incomeSourceRequestDTOs);
            List<IncomeSourceDTO> updatedIncomeSources = comprehensiveLeadService.saveOrUpdateIncomeSources(requestDTO);
            return ResponseEntity.ok(updatedIncomeSources);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ErrorResponse("Error updating income sources", e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ErrorResponse("Unexpected error", e.getMessage()));
        }
    }
    
    /**
     * Save related parties for a comprehensive lead
     * 
     * @param leadId The ID of the comprehensive lead
     * @param relatedPartyRequestDTOs List of related party request DTOs
     * @return ResponseEntity with list of RelatedPartyDTO and HTTP status
     */
    @PostMapping("/{leadId}/related-parties")
    public ResponseEntity<?> saveRelatedParties(
            @PathVariable Long leadId,
            @RequestBody List<RelatedPartyRequestDTO> relatedPartyRequestDTOs) {
        try {
            RelatedPartyListRequestDTO requestDTO = new RelatedPartyListRequestDTO();
            requestDTO.setLeadId(leadId);
            requestDTO.setRelatedParties(relatedPartyRequestDTOs);
            List<RelatedPartyDTO> savedRelatedParties = comprehensiveLeadService.saveOrUpdateRelatedParties(requestDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedRelatedParties);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ErrorResponse("Error saving related parties", e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ErrorResponse("Unexpected error", e.getMessage()));
        }
    }
    
    /**
     * Save or update related parties using leadId in the request body
     * 
     * @param relatedPartyListRequestDTO The request DTO containing leadId and related parties
     * @return ResponseEntity with list of RelatedPartyDTO and HTTP status
     */
    @PostMapping("/related-parties")
    public ResponseEntity<?> saveOrUpdateRelatedParties(@RequestBody RelatedPartyListRequestDTO relatedPartyListRequestDTO) {
        try {
            List<RelatedPartyDTO> savedRelatedParties = comprehensiveLeadService.saveOrUpdateRelatedParties(
                    relatedPartyListRequestDTO
            );
            return ResponseEntity.ok(savedRelatedParties);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ErrorResponse("Error saving related parties", e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ErrorResponse("Unexpected error", e.getMessage()));
        }
    }
    
    /**
     * Update related parties for a comprehensive lead by deleting existing records and recreating them
     * Uses the same saveRelatedParties method after deleting existing records for the specific lead ID
     * 
     * @param leadId The ID of the comprehensive lead
     * @param relatedPartyRequestDTOs List of related party request DTOs
     * @return ResponseEntity with list of RelatedPartyDTO and HTTP status
     */
    @PutMapping("/{leadId}/related-parties")
    public ResponseEntity<?> updateRelatedParties(
            @PathVariable Long leadId,
            @RequestBody List<RelatedPartyRequestDTO> relatedPartyRequestDTOs) {
        try {
            RelatedPartyListRequestDTO requestDTO = new RelatedPartyListRequestDTO();
            requestDTO.setLeadId(leadId);
            requestDTO.setRelatedParties(relatedPartyRequestDTOs);
            List<RelatedPartyDTO> updatedRelatedParties = comprehensiveLeadService.saveOrUpdateRelatedParties(requestDTO);
            return ResponseEntity.ok(updatedRelatedParties);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ErrorResponse("Error updating related parties", e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ErrorResponse("Unexpected error", e.getMessage()));
        }
    }
    
    /**
     * Get a comprehensive lead by ID with all child entities
     * 
     * @param leadId The ID of the comprehensive lead to retrieve
     * @return ResponseEntity with ComprehensiveLeadDTO and HTTP status
     */
    @GetMapping("/{leadId}")
    public ResponseEntity<?> getComprehensiveLeadById(@PathVariable Long leadId) {
        try {
            ComprehensiveLeadDTO comprehensiveLeadDTO = comprehensiveLeadService.getComprehensiveLeadById(leadId);
            return ResponseEntity.ok(comprehensiveLeadDTO);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ErrorResponse("Comprehensive Lead not found", e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ErrorResponse("Error retrieving comprehensive lead", e.getMessage()));
        }
    }

    /**
     * Soft-delete all child entities by setting status to INACTIVE
     *
     * @param leadId The ID of the comprehensive lead
     * @return ResponseEntity with success message and HTTP status
     */
    @DeleteMapping("/{leadId}/children")
    public ResponseEntity<?> deactivateChildrenForLead(@PathVariable Long leadId) {
        try {
            comprehensiveLeadService.deactivateChildrenForLead(leadId);
            return ResponseEntity.ok("Children deactivated successfully");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ErrorResponse("Error deactivating children", e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ErrorResponse("Unexpected error", e.getMessage()));
        }
    }
    
    /**
     * Update a comprehensive lead by deleting existing child records and recreating them
     * Uses the same child methods (savePartyForLead, saveIncomeSources, saveRelatedParties) 
     * after deleting existing records for the specific ID
     * 
     * @param leadId The ID of the comprehensive lead to update
     * @param comprehensiveLeadRequestDTO The comprehensive lead request DTO containing updated lead and party data
     * @return ResponseEntity with ComprehensiveLeadDTO and HTTP status
     */
    @PutMapping("/{leadId}")
    public ResponseEntity<?> updateLeadWithChildren(
            @PathVariable Long leadId,
            @RequestBody ComprehensiveLeadRequestDTO comprehensiveLeadRequestDTO) {
        try {
            comprehensiveLeadRequestDTO.setCompLeadId(leadId);
            ComprehensiveLeadDTO updatedLead = comprehensiveLeadService.saveOrUpdateLeadWithChildren(comprehensiveLeadRequestDTO);
            return ResponseEntity.ok(updatedLead);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ErrorResponse("Error updating comprehensive lead", e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ErrorResponse("Unexpected error", e.getMessage()));
        }
    }
    
    /**
     * Save or update a facility using compFacilityId in the request body
     * If compFacilityId is 0 or null, it creates a new facility using compLeadId
     * If compFacilityId is provided, it updates the existing facility
     * 
     * @param facilityDTO The facility DTO containing facility data
     * @return ResponseEntity with ComprehensiveFacilityDTO and HTTP status
     */
    @PostMapping("/facilities")
    public ResponseEntity<?> saveOrUpdateFacility(@RequestBody ComprehensiveFacilityDTO facilityDTO) {
        try {
            ComprehensiveFacilityDTO savedFacility = comprehensiveLeadService.saveOrUpdateFacility(facilityDTO);
            HttpStatus status = (facilityDTO.getCompFacilityId() != null && facilityDTO.getCompFacilityId() > 0)
                    ? HttpStatus.OK
                    : HttpStatus.CREATED;
            return ResponseEntity.status(status).body(savedFacility);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ErrorResponse("Error saving facility", e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ErrorResponse("Unexpected error", e.getMessage()));
        }
    }
    
    /**
     * Error response class for API error messages
     */
    public static class ErrorResponse {
        private String error;
        private String message;
        
        public ErrorResponse(String error, String message) {
            this.error = error;
            this.message = message;
        }
        
        public String getError() {
            return error;
        }
        
        public void setError(String error) {
            this.error = error;
        }
        
        public String getMessage() {
            return message;
        }
        
        public void setMessage(String message) {
            this.message = message;
        }
    }
}
