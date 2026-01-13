package com.example.lease_cal.dto.application;

import java.math.BigDecimal;
import java.time.LocalDate;

public class InsuranceConsentDTO {
    
    // Response fields
    private Long insuranceId;
    private Long applicationId;
    private LocalDate createdDate;
    private LocalDate modifiedDate;
    private String modifiedBy;
    
    // Request/Response fields
    private Boolean consent; // For request (will be converted to 'Y'/'N' in entity)
    private String consentString; // For response ('Y'/'N')
    private Boolean loanInsuranceRequired; // For request (will be converted to 'Y'/'N' in entity)
    private String loanInsuranceRequiredString; // For response ('Y'/'N')
    private BigDecimal insuranceAmount;
    private String insuranceCompany;
    private String createdBy;
    
    // Constructors
    public InsuranceConsentDTO() {
    }
    
    // Getters and Setters
    public Long getInsuranceId() {
        return insuranceId;
    }
    
    public void setInsuranceId(Long insuranceId) {
        this.insuranceId = insuranceId;
    }
    
    public Long getApplicationId() {
        return applicationId;
    }
    
    public void setApplicationId(Long applicationId) {
        this.applicationId = applicationId;
    }
    
    public Boolean getConsent() {
        return consent;
    }
    
    public void setConsent(Boolean consent) {
        this.consent = consent;
    }
    
    public String getConsentString() {
        return consentString;
    }
    
    public void setConsentString(String consentString) {
        this.consentString = consentString;
    }
    
    public Boolean getLoanInsuranceRequired() {
        return loanInsuranceRequired;
    }
    
    public void setLoanInsuranceRequired(Boolean loanInsuranceRequired) {
        this.loanInsuranceRequired = loanInsuranceRequired;
    }
    
    public String getLoanInsuranceRequiredString() {
        return loanInsuranceRequiredString;
    }
    
    public void setLoanInsuranceRequiredString(String loanInsuranceRequiredString) {
        this.loanInsuranceRequiredString = loanInsuranceRequiredString;
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
