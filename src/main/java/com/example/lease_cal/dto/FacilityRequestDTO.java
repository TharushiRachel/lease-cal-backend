package com.example.lease_cal.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public class FacilityRequestDTO {
    
    private Long facilityId;
    private Long applicationId;
    private String type;
    private String purpose;
    private BigDecimal amount;
    private Long repaymentYears;
    private String repaymentType;
    private String deductionDate;
    private BigDecimal applicantContribution;
    private BigDecimal advancePayment;
    private LocalDate createdDate;
    private String createdBy;
    private LocalDate modifiedDate;
    private String modifiedBy;
    
    // Constructors
    public FacilityRequestDTO() {
    }
    
    // Getters and Setters
    public Long getFacilityId() {
        return facilityId;
    }
    
    public void setFacilityId(Long facilityId) {
        this.facilityId = facilityId;
    }
    
    public Long getApplicationId() {
        return applicationId;
    }
    
    public void setApplicationId(Long applicationId) {
        this.applicationId = applicationId;
    }
    
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
