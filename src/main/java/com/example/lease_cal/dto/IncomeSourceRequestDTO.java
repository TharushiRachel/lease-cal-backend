package com.example.lease_cal.dto;

public class IncomeSourceRequestDTO {
    
    private String incomeType;
    private String considerForRepayment;
    
    // Constructors
    public IncomeSourceRequestDTO() {
    }
    
    // Getters and Setters
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

