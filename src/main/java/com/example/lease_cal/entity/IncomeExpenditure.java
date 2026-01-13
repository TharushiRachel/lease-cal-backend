package com.example.lease_cal.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "income_expenditure")
public class IncomeExpenditure {
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "income_expenditure_seq")
    @SequenceGenerator(name = "income_expenditure_seq", sequenceName = "seq_income_expenditure_id", allocationSize = 1)
    @Column(name = "record_id")
    private Long recordId;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "applicant_id")
    private ApplicantIndividual applicantIndividual;
    
    @Column(name = "income_source", length = 100)
    private String incomeSource;
    
    @Column(name = "monthly_income", precision = 18, scale = 2)
    private BigDecimal monthlyIncome;
    
    @Column(name = "expense_type", length = 100)
    private String expenseType;
    
    @Column(name = "monthly_expense", precision = 18, scale = 2)
    private BigDecimal monthlyExpense;
    
    @Column(name = "CREATED_DATE")
    private LocalDate createdDate;
    
    @Column(name = "CREATED_BY", length = 100)
    private String createdBy;
    
    @Column(name = "MODIFIED_DATE")
    private LocalDate modifiedDate;
    
    @Column(name = "MODIFIED_BY", length = 100)
    private String modifiedBy;
    
    @PrePersist
    protected void onCreate() {
        if (createdDate == null) {
            createdDate = LocalDate.now();
        }
    }
    
    @PreUpdate
    protected void onUpdate() {
        modifiedDate = LocalDate.now();
    }
    
    // Constructors
    public IncomeExpenditure() {
    }
    
    // Getters and Setters
    public Long getRecordId() {
        return recordId;
    }
    
    public void setRecordId(Long recordId) {
        this.recordId = recordId;
    }
    
    public ApplicantIndividual getApplicantIndividual() {
        return applicantIndividual;
    }
    
    public void setApplicantIndividual(ApplicantIndividual applicantIndividual) {
        this.applicantIndividual = applicantIndividual;
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
