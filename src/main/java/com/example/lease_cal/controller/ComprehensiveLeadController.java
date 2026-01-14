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
            ComprehensiveLeadDTO savedLead = comprehensiveLeadService.saveLeadWithChildren(comprehensiveLeadRequestDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedLead);
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
            return ResponseEntity.status(HttpStatus.CREATED).body(savedParty);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ErrorResponse("Error saving party", e.getMessage()));
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
            List<IncomeSourceDTO> savedIncomeSources = comprehensiveLeadService.saveIncomeSources(
                    partyId, 
                    incomeSourceRequestDTOs
            );
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
            List<IncomeSourceDTO> updatedIncomeSources = comprehensiveLeadService.updateIncomeSources(
                    partyId, 
                    incomeSourceRequestDTOs
            );
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
            List<RelatedPartyDTO> savedRelatedParties = comprehensiveLeadService.saveRelatedParties(
                    leadId, 
                    relatedPartyRequestDTOs
            );
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
            List<RelatedPartyDTO> updatedRelatedParties = comprehensiveLeadService.updateRelatedParties(
                    leadId, 
                    relatedPartyRequestDTOs
            );
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
            ComprehensiveLeadDTO updatedLead = comprehensiveLeadService.updateLeadWithChildren(leadId, comprehensiveLeadRequestDTO);
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
     * Save a facility for a comprehensive lead
     * 
     * @param leadId The ID of the comprehensive lead
     * @param facilityDTO The facility DTO containing facility data
     * @return ResponseEntity with ComprehensiveFacilityDTO and HTTP status
     */
    @PostMapping("/{leadId}/facilities")
    public ResponseEntity<?> saveFacility(
            @PathVariable Long leadId,
            @RequestBody ComprehensiveFacilityDTO facilityDTO) {
        try {
            ComprehensiveFacilityDTO savedFacility = comprehensiveLeadService.saveFacility(leadId, facilityDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedFacility);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ErrorResponse("Error saving facility", e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ErrorResponse("Unexpected error", e.getMessage()));
        }
    }
    
    /**
     * Update a facility by deleting existing record and recreating it
     * Uses the same saveFacility method after deleting existing record for the specific facility ID
     * 
     * @param facilityId The ID of the facility to update
     * @param facilityDTO The facility DTO containing updated facility data
     * @return ResponseEntity with ComprehensiveFacilityDTO and HTTP status
     */
    @PutMapping("/facilities/{facilityId}")
    public ResponseEntity<?> updateFacility(
            @PathVariable Long facilityId,
            @RequestBody ComprehensiveFacilityDTO facilityDTO) {
        try {
            ComprehensiveFacilityDTO updatedFacility = comprehensiveLeadService.updateFacility(facilityId, facilityDTO);
            return ResponseEntity.ok(updatedFacility);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ErrorResponse("Error updating facility", e.getMessage()));
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
