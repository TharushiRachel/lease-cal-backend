package com.example.lease_cal.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "income_tax")
public class IncomeTax {
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "income_tax_seq")
    @SequenceGenerator(name = "income_tax_seq", sequenceName = "seq_income_tax_id", allocationSize = 1)
    @Column(name = "tax_id")
    private Long taxId;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "application_id")
    private Application application;
    
    @Column(name = "year_of_assessment")
    private Long yearOfAssessment;
    
    @Column(name = "assessable_income", precision = 18, scale = 2)
    private BigDecimal assessableIncome;
    
    @Column(name = "taxable_income", precision = 18, scale = 2)
    private BigDecimal taxableIncome;
    
    @Column(name = "tax_paid", precision = 18, scale = 2)
    private BigDecimal taxPaid;
    
    @Column(name = "remarks", length = 2000)
    private String remarks;
    
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
    public IncomeTax() {
    }
    
    // Getters and Setters
    public Long getTaxId() {
        return taxId;
    }
    
    public void setTaxId(Long taxId) {
        this.taxId = taxId;
    }
    
    public Application getApplication() {
        return application;
    }
    
    public void setApplication(Application application) {
        this.application = application;
    }
    
    public Long getYearOfAssessment() {
        return yearOfAssessment;
    }
    
    public void setYearOfAssessment(Long yearOfAssessment) {
        this.yearOfAssessment = yearOfAssessment;
    }
    
    public BigDecimal getAssessableIncome() {
        return assessableIncome;
    }
    
    public void setAssessableIncome(BigDecimal assessableIncome) {
        this.assessableIncome = assessableIncome;
    }
    
    public BigDecimal getTaxableIncome() {
        return taxableIncome;
    }
    
    public void setTaxableIncome(BigDecimal taxableIncome) {
        this.taxableIncome = taxableIncome;
    }
    
    public BigDecimal getTaxPaid() {
        return taxPaid;
    }
    
    public void setTaxPaid(BigDecimal taxPaid) {
        this.taxPaid = taxPaid;
    }
    
    public String getRemarks() {
        return remarks;
    }
    
    public void setRemarks(String remarks) {
        this.remarks = remarks;
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
