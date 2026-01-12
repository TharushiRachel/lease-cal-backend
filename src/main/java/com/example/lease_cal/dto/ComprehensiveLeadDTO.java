package com.example.lease_cal.dto;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ComprehensiveLeadDTO {
    
    private Long compLeadId;
    private String leadName;
    private String creationType;
    private LocalDate createdDate;
    private String createdBy;
    private LocalDate modifiedDate;
    private String modifiedBy;
    private List<PartyDTO> parties;
    private List<RelatedPartyDTO> relatedParties;
    
    // Constructors
    public ComprehensiveLeadDTO() {
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
    
    public String getCreationType() {
        return creationType;
    }
    
    public void setCreationType(String creationType) {
        this.creationType = creationType;
    }
    
    public LocalDate getCreatedDate() {
        return createdDate;
    }
    
    public void setCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
    }
    
    public String getCreatedBy() {
        return createdBy;
    }
    
    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }
    
    public LocalDate getModifiedDate() {
        return modifiedDate;
    }
    
    public void setModifiedDate(LocalDate modifiedDate) {
        this.modifiedDate = modifiedDate;
    }
    
    public String getModifiedBy() {
        return modifiedBy;
    }
    
    public void setModifiedBy(String modifiedBy) {
        this.modifiedBy = modifiedBy;
    }
    
    public List<PartyDTO> getParties() {
        return parties;
    }
    
    public void setParties(List<PartyDTO> parties) {
        this.parties = parties;
    }
    
    public List<RelatedPartyDTO> getRelatedParties() {
        return relatedParties;
    }
    
    public void setRelatedParties(List<RelatedPartyDTO> relatedParties) {
        this.relatedParties = relatedParties;
    }
}
