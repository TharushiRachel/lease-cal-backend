package com.example.lease_cal.dto;

public class IncomeSourceRequestDTO {
    
    private String party;
    private String incomeType;
    private String considerForRepayment;
    
    // Constructors
    public IncomeSourceRequestDTO() {
    }
    
    // Getters and Setters
    public String getParty() {
        return party;
    }
    
    public void setParty(String party) {
        this.party = party;
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

