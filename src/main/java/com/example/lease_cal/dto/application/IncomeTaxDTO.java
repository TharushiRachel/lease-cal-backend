package com.example.lease_cal.dto.application;

import java.math.BigDecimal;
import java.time.LocalDate;

public class IncomeTaxDTO {
    
    // Response fields
    private Long taxId;
    private Long applicationId;
    private LocalDate createdDate;
    private LocalDate modifiedDate;
    private String modifiedBy;
    
    // Request/Response fields
    private Long yearOfAssessment;
    private BigDecimal assessableIncome;
    private BigDecimal taxableIncome;
    private BigDecimal taxPaid;
    private String remarks;
    private String createdBy;
    
    // Constructors
    public IncomeTaxDTO() {
    }
    
    // Getters and Setters
    public Long getTaxId() {
        return taxId;
    }
    
    public void setTaxId(Long taxId) {
        this.taxId = taxId;
    }
    
    public Long getApplicationId() {
        return applicationId;
    }
    
    public void setApplicationId(Long applicationId) {
        this.applicationId = applicationId;
    }
    
    public Long getYearOfAssessment() {
        return yearOfAssessment;
    }
    
    public void setYearOfAssessment(Long yearOfAssessment) {
        this.yearOfAssessment = yearOfAssessment;
    }
    
    public BigDecimal getAssessableIncome() {
        return assessableIncome;
    }
    
    public void setAssessableIncome(BigDecimal assessableIncome) {
        this.assessableIncome = assessableIncome;
    }
    
    public BigDecimal getTaxableIncome() {
        return taxableIncome;
    }
    
    public void setTaxableIncome(BigDecimal taxableIncome) {
        this.taxableIncome = taxableIncome;
    }
    
    public BigDecimal getTaxPaid() {
        return taxPaid;
    }
    
    public void setTaxPaid(BigDecimal taxPaid) {
        this.taxPaid = taxPaid;
    }
    
    public String getRemarks() {
        return remarks;
    }
    
    public void setRemarks(String remarks) {
        this.remarks = remarks;
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
