package com.example.lease_cal.dto;

import java.math.BigDecimal;

public class IncomeExpenditureRequestDTO {
    
    private String incomeSource;
    private BigDecimal monthlyIncome;
    private String expenseType;
    private BigDecimal monthlyExpense;
    private String createdBy;
    
    // Constructors
    public IncomeExpenditureRequestDTO() {
    }
    
    // Getters and Setters
    public String getIncomeSource() {
        return incomeSource;
    }
    
    public void setIncomeSource(String incomeSource) {
        this.incomeSource = incomeSource;
    }
    
    public BigDecimal getMonthlyIncome() {
        return monthlyIncome;
    }
    
    public void setMonthlyIncome(BigDecimal monthlyIncome) {
        this.monthlyIncome = monthlyIncome;
    }
    
    public String getExpenseType() {
        return expenseType;
    }
    
    public void setExpenseType(String expenseType) {
        this.expenseType = expenseType;
    }
    
    public BigDecimal getMonthlyExpense() {
        return monthlyExpense;
    }
    
    public void setMonthlyExpense(BigDecimal monthlyExpense) {
        this.monthlyExpense = monthlyExpense;
    }
    
    public String getCreatedBy() {
        return createdBy;
    }
    
    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }
}
