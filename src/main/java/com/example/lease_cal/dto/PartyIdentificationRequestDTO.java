package com.example.lease_cal.dto;

public class PartyIdentificationRequestDTO {
    
    private String identificationType;
    private String identificationNumber;
    private String createdBy;
    
    // Constructors
    public PartyIdentificationRequestDTO() {
    }
    
    // Getters and Setters
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
    
    public String getCreatedBy() {
        return createdBy;
    }
    
    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }
}

