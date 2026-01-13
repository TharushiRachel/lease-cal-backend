package com.example.lease_cal.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ApplicantCompanyRequestDTO {
    
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
    private List<CompanyDirectorRequestDTO> companyDirectors;
    
    // Constructors
    public ApplicantCompanyRequestDTO() {
        this.companyDirectors = new ArrayList<>();
    }
    
    // Getters and Setters
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
    
    public String getCreatedBy() {
        return createdBy;
    }
    
    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }
    
    public List<CompanyDirectorRequestDTO> getCompanyDirectors() {
        return companyDirectors;
    }
    
    public void setCompanyDirectors(List<CompanyDirectorRequestDTO> companyDirectors) {
        this.companyDirectors = companyDirectors;
    }
}
