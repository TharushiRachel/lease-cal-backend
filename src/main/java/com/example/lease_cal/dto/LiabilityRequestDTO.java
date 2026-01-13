package com.example.lease_cal.dto;

import java.math.BigDecimal;

public class LiabilityRequestDTO {
    
    private String creditorName;
    private String referenceNo;
    private String purpose;
    private BigDecimal amountBorrowed;
    private BigDecimal balancePayable;
    private Long repaymentPeriodMonths;
    private String security;
    private String createdBy;
    
    // Constructors
    public LiabilityRequestDTO() {
    }
    
    // Getters and Setters
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
    
    public String getCreatedBy() {
        return createdBy;
    }
    
    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }
}
