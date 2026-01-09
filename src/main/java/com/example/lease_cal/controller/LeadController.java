package com.example.lease_cal.controller;

import com.example.lease_cal.dto.LeadDTO;
import com.example.lease_cal.dto.LeadRequestDTO;
import com.example.lease_cal.service.LeadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/leads")
@CrossOrigin(origins = "*")
public class LeadController {
    
    @Autowired
    private LeadService leadService;
    
    /**
     * Create a new lead with all child entities (parties, identifications, addresses, income sources, related parties)
     * 
     * @param leadRequestDTO The lead request DTO containing all data
     * @return ResponseEntity with LeadDTO and HTTP status
     */
    @PostMapping
    public ResponseEntity<?> saveLeadWithChildren(@RequestBody LeadRequestDTO leadRequestDTO) {
        try {
            LeadDTO savedLead = leadService.saveLeadWithChildren(leadRequestDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedLead);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ErrorResponse("Validation Error", e.getMessage()));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ErrorResponse("Error saving lead", e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ErrorResponse("Unexpected error", e.getMessage()));
        }
    }
    
    /**
     * Get a lead by ID with all child entities
     * 
     * @param leadId The ID of the lead to retrieve
     * @return ResponseEntity with LeadDTO and HTTP status
     */
    @GetMapping("/{leadId}")
    public ResponseEntity<?> getLeadById(@PathVariable Long leadId) {
        try {
            LeadDTO leadDTO = leadService.getLeadById(leadId);
            return ResponseEntity.ok(leadDTO);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ErrorResponse("Lead not found", e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ErrorResponse("Error retrieving lead", e.getMessage()));
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

