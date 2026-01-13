package com.example.lease_cal.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public class InsuranceConsentDTO {
    
    private Long insuranceId;
    private Long applicationId;
    private String consent;
    private String loanInsuranceRequired;
    private BigDecimal insuranceAmount;
    private String insuranceCompany;
    private LocalDate createdDate;
    private String createdBy;
    private LocalDate modifiedDate;
    private String modifiedBy;
    
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
    
    public String getConsent() {
        return consent;
    }
    
    public void setConsent(String consent) {
        this.consent = consent;
    }
    
    public String getLoanInsuranceRequired() {
        return loanInsuranceRequired;
    }
    
    public void setLoanInsuranceRequired(String loanInsuranceRequired) {
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
