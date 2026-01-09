package com.example.lease_cal.dto;

import java.time.LocalDate;

public class PartyIdentificationDTO {
    
    private Long identificationId;
    private Long compPartyId;
    private String identificationType;
    private String identificationNumber;
    private LocalDate createdDate;
    private String createdBy;
    private LocalDate modifiedDate;
    private String modifiedBy;
    
    // Constructors
    public PartyIdentificationDTO() {
    }
    
    // Getters and Setters
    public Long getIdentificationId() {
        return identificationId;
    }
    
    public void setIdentificationId(Long identificationId) {
        this.identificationId = identificationId;
    }
    
    public Long getCompPartyId() {
        return compPartyId;
    }
    
    public void setCompPartyId(Long compPartyId) {
        this.compPartyId = compPartyId;
    }
    
    public String getIdentificationType() {
        return identificationType;
    }
    
    public void setIdentificationType(String identificationType) {
        this.identificationType = identificationType;
    }
    
    public String getIdentificationNumber() {
        return identificationNumber;
    }
    
    public void setIdentificationNumber(String identificationNumber) {
        this.identificationNumber = identificationNumber;
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
}

