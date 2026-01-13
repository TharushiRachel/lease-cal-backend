package com.example.lease_cal.dto;

public class EmploymentDetailsRequestDTO {
    
    private String employerName;
    private String employmentType;
    private String designation;
    private Long yearsWithEmployer;
    private String businessNature;
    private String employerAddress;
    private String officePhone;
    private String avgIncomeGrowth; // '>10%','>5%','<=5%','POSITIVE_ONE_YEAR','NEGATIVE_BOTH'
    private String createdBy;
    
    // Constructors
    public EmploymentDetailsRequestDTO() {
    }
    
    // Getters and Setters
    public String getEmployerName() {
        return employerName;
    }
    
    public void setEmployerName(String employerName) {
        this.employerName = employerName;
    }
    
    public String getEmploymentType() {
        return employmentType;
    }
    
    public void setEmploymentType(String employmentType) {
        this.employmentType = employmentType;
    }
    
    public String getDesignation() {
        return designation;
    }
    
    public void setDesignation(String designation) {
        this.designation = designation;
    }
    
    public Long getYearsWithEmployer() {
        return yearsWithEmployer;
    }
    
    public void setYearsWithEmployer(Long yearsWithEmployer) {
        this.yearsWithEmployer = yearsWithEmployer;
    }
    
    public String getBusinessNature() {
        return businessNature;
    }
    
    public void setBusinessNature(String businessNature) {
        this.businessNature = businessNature;
    }
    
    public String getEmployerAddress() {
        return employerAddress;
    }
    
    public void setEmployerAddress(String employerAddress) {
        this.employerAddress = employerAddress;
    }
    
    public String getOfficePhone() {
        return officePhone;
    }
    
    public void setOfficePhone(String officePhone) {
        this.officePhone = officePhone;
    }
    
    public String getAvgIncomeGrowth() {
        return avgIncomeGrowth;
    }
    
    public void setAvgIncomeGrowth(String avgIncomeGrowth) {
        this.avgIncomeGrowth = avgIncomeGrowth;
    }
    
    public String getCreatedBy() {
        return createdBy;
    }
    
    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }
}
