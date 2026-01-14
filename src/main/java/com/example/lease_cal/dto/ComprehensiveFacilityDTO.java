package com.example.lease_cal.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public class ComprehensiveFacilityDTO {
    
    private Long compFacilityId;
    private Long compLeadId;
    private String facilityDescription;
    private Integer requestedTenure; // in months
    private BigDecimal leaseRental;
    private BigDecimal processingFee;
    private LocalDate validityOfOffer;
    private BigDecimal leaseAmount;
    private String repaymentMode;
    private BigDecimal upfront;
    private BigDecimal insurance;
    private LocalDate createdDate;
    private String createdBy;
    private LocalDate modifiedDate;
    private String modifiedBy;
    
    // Constructors
    public ComprehensiveFacilityDTO() {
    }
    
    // Getters and Setters
    public Long getCompFacilityId() {
        return compFacilityId;
    }
    
    public void setCompFacilityId(Long compFacilityId) {
        this.compFacilityId = compFacilityId;
    }
    
    public Long getCompLeadId() {
        return compLeadId;
    }
    
    public void setCompLeadId(Long compLeadId) {
        this.compLeadId = compLeadId;
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
