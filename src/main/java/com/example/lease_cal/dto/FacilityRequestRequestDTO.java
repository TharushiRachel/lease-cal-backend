package com.example.lease_cal.dto;

import java.math.BigDecimal;

public class FacilityRequestRequestDTO {
    
    private String type;
    private String purpose;
    private BigDecimal amount;
    private Long repaymentYears;
    private String repaymentType;
    private String deductionDate; // '04' or '26'
    private BigDecimal applicantContribution;
    private BigDecimal advancePayment;
    private String createdBy;
    
    // Constructors
    public FacilityRequestRequestDTO() {
    }
    
    // Getters and Setters
    public String getType() {
        return type;
    }
    
    public void setType(String type) {
        this.type = type;
    }
    
    public String getPurpose() {
        return purpose;
    }
    
    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }
    
    public BigDecimal getAmount() {
        return amount;
    }
    
    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
    
    public Long getRepaymentYears() {
        return repaymentYears;
    }
    
    public void setRepaymentYears(Long repaymentYears) {
        this.repaymentYears = repaymentYears;
    }
    
    public String getRepaymentType() {
        return repaymentType;
    }
    
    public void setRepaymentType(String repaymentType) {
        this.repaymentType = repaymentType;
    }
    
    public String getDeductionDate() {
        return deductionDate;
    }
    
    public void setDeductionDate(String deductionDate) {
        this.deductionDate = deductionDate;
    }
    
    public BigDecimal getApplicantContribution() {
        return applicantContribution;
    }
    
    public void setApplicantContribution(BigDecimal applicantContribution) {
        this.applicantContribution = applicantContribution;
    }
    
    public BigDecimal getAdvancePayment() {
        return advancePayment;
    }
    
    public void setAdvancePayment(BigDecimal advancePayment) {
        this.advancePayment = advancePayment;
    }
    
    public String getCreatedBy() {
        return createdBy;
    }
    
    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }
}
