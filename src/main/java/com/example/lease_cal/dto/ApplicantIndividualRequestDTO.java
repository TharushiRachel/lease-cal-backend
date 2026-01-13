package com.example.lease_cal.dto;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ApplicantIndividualRequestDTO {
    
    private String fullName;
    private LocalDate dateOfBirth;
    private String nicPassportNo;
    private String permanentAddress;
    private String communicationAddress;
    private String residenceType; // 'OWN','RENTED','OTHER'
    private Long yearsAtAddress;
    private String nationality;
    private Boolean isPep; // Will be converted to 'Y'/'N'
    private String pepDetails;
    private String civilStatus;
    private Long dependentsCount;
    private String highestQualification;
    private String professionalQualification;
    private String createdBy;
    private List<EmploymentDetailsRequestDTO> employmentDetails;
    private List<IncomeExpenditureRequestDTO> incomeExpenditures;
    
    // Constructors
    public ApplicantIndividualRequestDTO() {
        this.employmentDetails = new ArrayList<>();
        this.incomeExpenditures = new ArrayList<>();
    }
    
    // Getters and Setters
    public String getFullName() {
        return fullName;
    }
    
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
    
    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }
    
    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }
    
    public String getNicPassportNo() {
        return nicPassportNo;
    }
    
    public void setNicPassportNo(String nicPassportNo) {
        this.nicPassportNo = nicPassportNo;
    }
    
    public String getPermanentAddress() {
        return permanentAddress;
    }
    
    public void setPermanentAddress(String permanentAddress) {
        this.permanentAddress = permanentAddress;
    }
    
    public String getCommunicationAddress() {
        return communicationAddress;
    }
    
    public void setCommunicationAddress(String communicationAddress) {
        this.communicationAddress = communicationAddress;
    }
    
    public String getResidenceType() {
        return residenceType;
    }
    
    public void setResidenceType(String residenceType) {
        this.residenceType = residenceType;
    }
    
    public Long getYearsAtAddress() {
        return yearsAtAddress;
    }
    
    public void setYearsAtAddress(Long yearsAtAddress) {
        this.yearsAtAddress = yearsAtAddress;
    }
    
    public String getNationality() {
        return nationality;
    }
    
    public void setNationality(String nationality) {
        this.nationality = nationality;
    }
    
    public Boolean getIsPep() {
        return isPep;
    }
    
    public void setIsPep(Boolean isPep) {
        this.isPep = isPep;
    }
    
    public String getPepDetails() {
        return pepDetails;
    }
    
    public void setPepDetails(String pepDetails) {
        this.pepDetails = pepDetails;
    }
    
    public String getCivilStatus() {
        return civilStatus;
    }
    
    public void setCivilStatus(String civilStatus) {
        this.civilStatus = civilStatus;
    }
    
    public Long getDependentsCount() {
        return dependentsCount;
    }
    
    public void setDependentsCount(Long dependentsCount) {
        this.dependentsCount = dependentsCount;
    }
    
    public String getHighestQualification() {
        return highestQualification;
    }
    
    public void setHighestQualification(String highestQualification) {
        this.highestQualification = highestQualification;
    }
    
    public String getProfessionalQualification() {
        return professionalQualification;
    }
    
    public void setProfessionalQualification(String professionalQualification) {
        this.professionalQualification = professionalQualification;
    }
    
    public String getCreatedBy() {
        return createdBy;
    }
    
    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }
    
    public List<EmploymentDetailsRequestDTO> getEmploymentDetails() {
        return employmentDetails;
    }
    
    public void setEmploymentDetails(List<EmploymentDetailsRequestDTO> employmentDetails) {
        this.employmentDetails = employmentDetails;
    }
    
    public List<IncomeExpenditureRequestDTO> getIncomeExpenditures() {
        return incomeExpenditures;
    }
    
    public void setIncomeExpenditures(List<IncomeExpenditureRequestDTO> incomeExpenditures) {
        this.incomeExpenditures = incomeExpenditures;
    }
}
