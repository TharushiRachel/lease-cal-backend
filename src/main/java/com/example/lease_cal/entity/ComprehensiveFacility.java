package com.example.lease_cal.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "t_comp_facilities")
public class ComprehensiveFacility {
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "comp_facility_seq")
    @SequenceGenerator(name = "comp_facility_seq", sequenceName = "seq_comp_facility_id", allocationSize = 1)
    @Column(name = "comp_facility_id")
    private Long compFacilityId;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "comp_lead_id", nullable = false)
    private ComprehensiveLead lead;
    
    @Column(name = "facility_description", length = 1000)
    private String facilityDescription;
    
    @Column(name = "requested_tenure")
    private Integer requestedTenure; // in months
    
    @Column(name = "lease_rental", precision = 18, scale = 2)
    private BigDecimal leaseRental;
    
    @Column(name = "processing_fee", precision = 18, scale = 2)
    private BigDecimal processingFee;
    
    @Column(name = "validity_of_offer")
    private LocalDate validityOfOffer;
    
    @Column(name = "lease_amount", precision = 18, scale = 2)
    private BigDecimal leaseAmount;
    
    @Column(name = "repayment_mode", length = 50)
    private String repaymentMode;
    
    @Column(name = "upfront", precision = 18, scale = 2)
    private BigDecimal upfront;
    
    @Column(name = "insurance", precision = 18, scale = 2)
    private BigDecimal insurance;
    
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
    public ComprehensiveFacility() {
    }
    
    public ComprehensiveFacility(ComprehensiveLead lead) {
        this.lead = lead;
    }
    
    // Getters and Setters
    public Long getCompFacilityId() {
        return compFacilityId;
    }
    
    public void setCompFacilityId(Long compFacilityId) {
        this.compFacilityId = compFacilityId;
    }
    
    public ComprehensiveLead getLead() {
        return lead;
    }
    
    public void setLead(ComprehensiveLead lead) {
        this.lead = lead;
    }
    
    public String getFacilityDescription() {
        return facilityDescription;
    }
    
    public void setFacilityDescription(String facilityDescription) {
        this.facilityDescription = facilityDescription;
    }
    
    public Integer getRequestedTenure() {
        return requestedTenure;
    }
    
    public void setRequestedTenure(Integer requestedTenure) {
        this.requestedTenure = requestedTenure;
    }
    
    public BigDecimal getLeaseRental() {
        return leaseRental;
    }
    
    public void setLeaseRental(BigDecimal leaseRental) {
        this.leaseRental = leaseRental;
    }
    
    public BigDecimal getProcessingFee() {
        return processingFee;
    }
    
    public void setProcessingFee(BigDecimal processingFee) {
        this.processingFee = processingFee;
    }
    
    public LocalDate getValidityOfOffer() {
        return validityOfOffer;
    }
    
    public void setValidityOfOffer(LocalDate validityOfOffer) {
        this.validityOfOffer = validityOfOffer;
    }
    
    public BigDecimal getLeaseAmount() {
        return leaseAmount;
    }
    
    public void setLeaseAmount(BigDecimal leaseAmount) {
        this.leaseAmount = leaseAmount;
    }
    
    public String getRepaymentMode() {
        return repaymentMode;
    }
    
    public void setRepaymentMode(String repaymentMode) {
        this.repaymentMode = repaymentMode;
    }
    
    public BigDecimal getUpfront() {
        return upfront;
    }
    
    public void setUpfront(BigDecimal upfront) {
        this.upfront = upfront;
    }
    
    public BigDecimal getInsurance() {
        return insurance;
    }
    
    public void setInsurance(BigDecimal insurance) {
        this.insurance = insurance;
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
