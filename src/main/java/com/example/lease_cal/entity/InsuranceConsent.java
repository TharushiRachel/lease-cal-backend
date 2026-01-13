package com.example.lease_cal.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "insurance_consent")
public class InsuranceConsent {
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "insurance_consent_seq")
    @SequenceGenerator(name = "insurance_consent_seq", sequenceName = "seq_insurance_consent_id", allocationSize = 1)
    @Column(name = "insurance_id")
    private Long insuranceId;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "application_id")
    private Application application;
    
    @Column(name = "consent", length = 1)
    private String consent; // 'Y' or 'N'
    
    @Column(name = "loan_insurance_required", length = 1)
    private String loanInsuranceRequired; // 'Y' or 'N'
    
    @Column(name = "insurance_amount", precision = 18, scale = 2)
    private BigDecimal insuranceAmount;
    
    @Column(name = "insurance_company", length = 255)
    private String insuranceCompany;
    
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
    public InsuranceConsent() {
    }
    
    // Getters and Setters
    public Long getInsuranceId() {
        return insuranceId;
    }
    
    public void setInsuranceId(Long insuranceId) {
        this.insuranceId = insuranceId;
    }
    
    public Application getApplication() {
        return application;
    }
    
    public void setApplication(Application application) {
        this.application = application;
    }
    
    public String getConsent() {
        return consent;
    }
    
    public void setConsent(String consent) {
        this.consent = consent;
    }
    
    public String getLoanInsuranceRequired() {
        return loanInsuranceRequired;
    }
    
    public void setLoanInsuranceRequired(String loanInsuranceRequired) {
        this.loanInsuranceRequired = loanInsuranceRequired;
    }
    
    public BigDecimal getInsuranceAmount() {
        return insuranceAmount;
    }
    
    public void setInsuranceAmount(BigDecimal insuranceAmount) {
        this.insuranceAmount = insuranceAmount;
    }
    
    public String getInsuranceCompany() {
        return insuranceCompany;
    }
    
    public void setInsuranceCompany(String insuranceCompany) {
        this.insuranceCompany = insuranceCompany;
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
