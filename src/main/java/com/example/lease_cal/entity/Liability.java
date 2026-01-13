package com.example.lease_cal.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "liabilities")
public class Liability {
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "liabilities_seq")
    @SequenceGenerator(name = "liabilities_seq", sequenceName = "seq_liabilities_id", allocationSize = 1)
    @Column(name = "liability_id")
    private Long liabilityId;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "application_id")
    private Application application;
    
    @Column(name = "creditor_name", length = 255)
    private String creditorName;
    
    @Column(name = "reference_no", length = 100)
    private String referenceNo;
    
    @Column(name = "purpose", length = 255)
    private String purpose;
    
    @Column(name = "amount_borrowed", precision = 18, scale = 2)
    private BigDecimal amountBorrowed;
    
    @Column(name = "balance_payable", precision = 18, scale = 2)
    private BigDecimal balancePayable;
    
    @Column(name = "repayment_period_months")
    private Long repaymentPeriodMonths;
    
    @Column(name = "security", length = 2000)
    private String security;
    
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
    public Liability() {
    }
    
    // Getters and Setters
    public Long getLiabilityId() {
        return liabilityId;
    }
    
    public void setLiabilityId(Long liabilityId) {
        this.liabilityId = liabilityId;
    }
    
    public Application getApplication() {
        return application;
    }
    
    public void setApplication(Application application) {
        this.application = application;
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
