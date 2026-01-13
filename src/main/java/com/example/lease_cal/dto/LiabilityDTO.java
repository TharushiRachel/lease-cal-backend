package com.example.lease_cal.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public class LiabilityDTO {
    
    private Long liabilityId;
    private Long applicationId;
    private String creditorName;
    private String referenceNo;
    private String purpose;
    private BigDecimal amountBorrowed;
    private BigDecimal balancePayable;
    private Long repaymentPeriodMonths;
    private String security;
    private LocalDate createdDate;
    private String createdBy;
    private LocalDate modifiedDate;
    private String modifiedBy;
    
    // Constructors
    public LiabilityDTO() {
    }
    
    // Getters and Setters
    public Long getLiabilityId() {
        return liabilityId;
    }
    
    public void setLiabilityId(Long liabilityId) {
        this.liabilityId = liabilityId;
    }
    
    public Long getApplicationId() {
        return applicationId;
    }
    
    public void setApplicationId(Long applicationId) {
        this.applicationId = applicationId;
    }
    
    public String getCreditorName() {
        return creditorName;
    }
    
    public void setCreditorName(String creditorName) {
        this.creditorName = creditorName;
    }
    
    public String getReferenceNo() {
        return referenceNo;
    }
    
    public void setReferenceNo(String referenceNo) {
        this.referenceNo = referenceNo;
    }
    
    public String getPurpose() {
        return purpose;
    }
    
    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }
    
    public BigDecimal getAmountBorrowed() {
        return amountBorrowed;
    }
    
    public void setAmountBorrowed(BigDecimal amountBorrowed) {
        this.amountBorrowed = amountBorrowed;
    }
    
    public BigDecimal getBalancePayable() {
        return balancePayable;
    }
    
    public void setBalancePayable(BigDecimal balancePayable) {
        this.balancePayable = balancePayable;
    }
    
    public Long getRepaymentPeriodMonths() {
        return repaymentPeriodMonths;
    }
    
    public void setRepaymentPeriodMonths(Long repaymentPeriodMonths) {
        this.repaymentPeriodMonths = repaymentPeriodMonths;
    }
    
    public String getSecurity() {
        return security;
    }
    
    public void setSecurity(String security) {
        this.security = security;
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
