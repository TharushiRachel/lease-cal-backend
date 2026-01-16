package com.example.lease_cal.dto;

import java.util.ArrayList;
import java.util.List;

public class ComprehensiveLeadRequestDTO {
    
    private Long compLeadId;
    private String leadName;
    private String createdBy;
    private List<PartyRequestDTO> parties;
    private List<RelatedPartyRequestDTO> relatedParties;
    
    // Constructors
    public ComprehensiveLeadRequestDTO() {
        this.parties = new ArrayList<>();
        this.relatedParties = new ArrayList<>();
    }
    
    // Getters and Setters
    public Long getCompLeadId() {
        return compLeadId;
    }
    
    public void setCompLeadId(Long compLeadId) {
        this.compLeadId = compLeadId;
    }
    
    public String getLeadName() {
        return leadName;
    }
    
    public void setLeadName(String leadName) {
        this.leadName = leadName;
    }
    
    public String getCreatedBy() {
        return createdBy;
    }
    
    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }
    
    public List<PartyRequestDTO> getParties() {
        return parties;
    }
    
    public void setParties(List<PartyRequestDTO> parties) {
        this.parties = parties;
    }
    
    public List<RelatedPartyRequestDTO> getRelatedParties() {
        return relatedParties;
    }
    
    public void setRelatedParties(List<RelatedPartyRequestDTO> relatedParties) {
        this.relatedParties = relatedParties;
    }
}
