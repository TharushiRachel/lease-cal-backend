package com.example.lease_cal.dto.application;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ApplicantCompanyDTO {
    
    // Response fields
    private Long companyId;
    private Long applicationId;
    private LocalDate createdDate;
    private LocalDate modifiedDate;
    private String modifiedBy;
    
    // Request/Response fields
    private String name;
    private String address;
    private String registrationNo;
    private LocalDate incorporationDate;
    private String businessType;
    private String constitution; // 'PRIVATE LTD','PUBLIC LTD','CORPORATION','OFF-SHORE'
    private Long noOfEmployees;
    private BigDecimal paidUpCapital;
    private BigDecimal issuedCapital;
    private String createdBy;
    private List<CompanyDirectorDTO> companyDirectors;
    
    // Constructors
    public ApplicantCompanyDTO() {
        this.companyDirectors = new ArrayList<>();
    }
    
    // Getters and Setters
    public Long getCompanyId() {
        return companyId;
    }
    
    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }
    
    public Long getApplicationId() {
        return applicationId;
    }
    
    public void setApplicationId(Long applicationId) {
        this.applicationId = applicationId;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getAddress() {
        return address;
    }
    
    public void setAddress(String address) {
        this.address = address;
    }
    
    public String getRegistrationNo() {
        return registrationNo;
    }
    
    public void setRegistrationNo(String registrationNo) {
        this.registrationNo = registrationNo;
    }
    
    public LocalDate getIncorporationDate() {
        return incorporationDate;
    }
    
    public void setIncorporationDate(LocalDate incorporationDate) {
        this.incorporationDate = incorporationDate;
    }
    
    public String getBusinessType() {
        return businessType;
    }
    
    public void setBusinessType(String businessType) {
        this.businessType = businessType;
    }
    
    public String getConstitution() {
        return constitution;
    }
    
    public void setConstitution(String constitution) {
        this.constitution = constitution;
    }
    
    public Long getNoOfEmployees() {
        return noOfEmployees;
    }
    
    public void setNoOfEmployees(Long noOfEmployees) {
        this.noOfEmployees = noOfEmployees;
    }
    
    public BigDecimal getPaidUpCapital() {
        return paidUpCapital;
    }
    
    public void setPaidUpCapital(BigDecimal paidUpCapital) {
        this.paidUpCapital = paidUpCapital;
    }
    
    public BigDecimal getIssuedCapital() {
        return issuedCapital;
    }
    
    public void setIssuedCapital(BigDecimal issuedCapital) {
        this.issuedCapital = issuedCapital;
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
    
    public List<CompanyDirectorDTO> getCompanyDirectors() {
        return companyDirectors;
    }
    
    public void setCompanyDirectors(List<CompanyDirectorDTO> companyDirectors) {
        this.companyDirectors = companyDirectors;
    }
}
