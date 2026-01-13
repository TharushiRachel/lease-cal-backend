package com.example.lease_cal.dto;

import java.math.BigDecimal;

public class IncomeTaxRequestDTO {
    
    private Long yearOfAssessment;
    private BigDecimal assessableIncome;
    private BigDecimal taxableIncome;
    private BigDecimal taxPaid;
    private String remarks;
    private String createdBy;
    
    // Constructors
    public IncomeTaxRequestDTO() {
    }
    
    // Getters and Setters
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
    
    public String getCreatedBy() {
        return createdBy;
    }
    
    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }
}
