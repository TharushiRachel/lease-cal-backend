package com.example.lease_cal.dto.application;

import java.math.BigDecimal;
import java.time.LocalDate;

public class CompanyDirectorDTO {
    
    // Response fields
    private Long directorId;
    private Long companyId;
    private LocalDate createdDate;
    private LocalDate modifiedDate;
    private String modifiedBy;
    
    // Request/Response fields
    private String name;
    private String address;
    private String nicNo;
    private Long age;
    private BigDecimal shareholdingPercent;
    private String createdBy;
    
    // Constructors
    public CompanyDirectorDTO() {
    }
    
    // Getters and Setters
    public Long getDirectorId() {
        return directorId;
    }
    
    public void setDirectorId(Long directorId) {
        this.directorId = directorId;
    }
    
    public Long getCompanyId() {
        return companyId;
    }
    
    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
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
    
    public String getNicNo() {
        return nicNo;
    }
    
    public void setNicNo(String nicNo) {
        this.nicNo = nicNo;
    }
    
    public Long getAge() {
        return age;
    }
    
    public void setAge(Long age) {
        this.age = age;
    }
    
    public BigDecimal getShareholdingPercent() {
        return shareholdingPercent;
    }
    
    public void setShareholdingPercent(BigDecimal shareholdingPercent) {
        this.shareholdingPercent = shareholdingPercent;
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
