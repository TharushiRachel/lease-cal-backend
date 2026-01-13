package com.example.lease_cal.dto;

import java.math.BigDecimal;

public class InsuranceConsentRequestDTO {
    
    private Boolean consent; // Will be converted to 'Y'/'N'
    private Boolean loanInsuranceRequired; // Will be converted to 'Y'/'N'
    private BigDecimal insuranceAmount;
    private String insuranceCompany;
    private String createdBy;
    
    // Constructors
    public InsuranceConsentRequestDTO() {
    }
    
    // Getters and Setters
    public Boolean getConsent() {
        return consent;
    }
    
    public void setConsent(Boolean consent) {
        this.consent = consent;
    }
    
    public Boolean getLoanInsuranceRequired() {
        return loanInsuranceRequired;
    }
    
    public void setLoanInsuranceRequired(Boolean loanInsuranceRequired) {
        this.loanInsuranceRequired = loanInsuranceRequired;
    }
    
    public BigDecimal getInsuranceAmount() {
        return insuranceAmount;
    }
    
    public void setInsuranceAmount(BigDecimal insuranceAmount) {
        this.insuranceAmount = insuranceAmount;
    }
    
    public String getInsuranceCompany() {
        return insuranceCompany;
    }
    
    public void setInsuranceCompany(String insuranceCompany) {
        this.insuranceCompany = insuranceCompany;
    }
    
    public String getCreatedBy() {
        return createdBy;
    }
    
    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }
}
