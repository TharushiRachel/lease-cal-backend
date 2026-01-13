package com.example.lease_cal.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "facility_request")
public class FacilityRequest {
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "facility_request_seq")
    @SequenceGenerator(name = "facility_request_seq", sequenceName = "seq_facility_request_id", allocationSize = 1)
    @Column(name = "facility_id")
    private Long facilityId;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "application_id")
    private Application application;
    
    @Column(name = "type", length = 100)
    private String type;
    
    @Column(name = "purpose", length = 2000)
    private String purpose;
    
    @Column(name = "amount", precision = 18, scale = 2)
    private BigDecimal amount;
    
    @Column(name = "repayment_years")
    private Long repaymentYears;
    
    @Column(name = "repayment_type", length = 100)
    private String repaymentType;
    
    @Column(name = "deduction_date", length = 2)
    private String deductionDate; // '04' or '26'
    
    @Column(name = "applicant_contribution", precision = 18, scale = 2)
    private BigDecimal applicantContribution;
    
    @Column(name = "advance_payment", precision = 18, scale = 2)
    private BigDecimal advancePayment;
    
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
    public FacilityRequest() {
    }
    
    // Getters and Setters
    public Long getFacilityId() {
        return facilityId;
    }
    
    public void setFacilityId(Long facilityId) {
        this.facilityId = facilityId;
    }
    
    public Application getApplication() {
        return application;
    }
    
    public void setApplication(Application application) {
        this.application = application;
    }
    
    public String getType() {
        return type;
    }
    
    public void setType(String type) {
        this.type = type;
    }
    
    public String getPurpose() {
        return purpose;
    }
    
    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }
    
    public BigDecimal getAmount() {
        return amount;
    }
    
    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
    
    public Long getRepaymentYears() {
        return repaymentYears;
    }
    
    public void setRepaymentYears(Long repaymentYears) {
        this.repaymentYears = repaymentYears;
    }
    
    public String getRepaymentType() {
        return repaymentType;
    }
    
    public void setRepaymentType(String repaymentType) {
        this.repaymentType = repaymentType;
    }
    
    public String getDeductionDate() {
        return deductionDate;
    }
    
    public void setDeductionDate(String deductionDate) {
        this.deductionDate = deductionDate;
    }
    
    public BigDecimal getApplicantContribution() {
        return applicantContribution;
    }
    
    public void setApplicantContribution(BigDecimal applicantContribution) {
        this.applicantContribution = applicantContribution;
    }
    
    public BigDecimal getAdvancePayment() {
        return advancePayment;
    }
    
    public void setAdvancePayment(BigDecimal advancePayment) {
        this.advancePayment = advancePayment;
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
