package com.example.lease_cal.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public class IncomeExpenditureDTO {
    
    private Long recordId;
    private Long applicantId;
    private String incomeSource;
    private BigDecimal monthlyIncome;
    private String expenseType;
    private BigDecimal monthlyExpense;
    private LocalDate createdDate;
    private String createdBy;
    private LocalDate modifiedDate;
    private String modifiedBy;
    
    // Constructors
    public IncomeExpenditureDTO() {
    }
    
    // Getters and Setters
    public Long getRecordId() {
        return recordId;
    }
    
    public void setRecordId(Long recordId) {
        this.recordId = recordId;
    }
    
    public Long getApplicantId() {
        return applicantId;
    }
    
    public void setApplicantId(Long applicantId) {
        this.applicantId = applicantId;
    }
    
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
