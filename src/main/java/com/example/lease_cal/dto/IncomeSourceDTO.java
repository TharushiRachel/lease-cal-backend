package com.example.lease_cal.dto;

public class IncomeSourceDTO {
    
    private Long incomeSourceId;
    private Long compPartyId;
    private String incomeType;
    private String considerForRepayment;
    
    // Constructors
    public IncomeSourceDTO() {
    }
    
    // Getters and Setters
    public Long getIncomeSourceId() {
        return incomeSourceId;
    }
    
    public void setIncomeSourceId(Long incomeSourceId) {
        this.incomeSourceId = incomeSourceId;
    }
    
    public Long getCompPartyId() {
        return compPartyId;
    }
    
    public void setCompPartyId(Long compPartyId) {
        this.compPartyId = compPartyId;
    }
    
    public String getIncomeType() {
        return incomeType;
    }
    
    public void setIncomeType(String incomeType) {
        this.incomeType = incomeType;
    }
    
    public String getConsiderForRepayment() {
        return considerForRepayment;
    }
    
    public void setConsiderForRepayment(String considerForRepayment) {
        this.considerForRepayment = considerForRepayment;
    }
}

