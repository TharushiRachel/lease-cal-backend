package com.example.lease_cal.dto;

import java.util.ArrayList;
import java.util.List;

public class RelatedPartyListRequestDTO {
    
    private Long leadId;
    private List<RelatedPartyRequestDTO> relatedParties;
    
    // Constructors
    public RelatedPartyListRequestDTO() {
        this.relatedParties = new ArrayList<>();
    }
    
    // Getters and Setters
    public Long getLeadId() {
        return leadId;
    }
    
    public void setLeadId(Long leadId) {
        this.leadId = leadId;
    }
    
    public List<RelatedPartyRequestDTO> getRelatedParties() {
        return relatedParties;
    }
    
    public void setRelatedParties(List<RelatedPartyRequestDTO> relatedParties) {
        this.relatedParties = relatedParties;
    }
}
