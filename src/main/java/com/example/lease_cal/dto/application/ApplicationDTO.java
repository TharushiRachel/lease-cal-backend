package com.example.lease_cal.dto.application;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ApplicationDTO {
    
    // Response fields
    private Long applicationId;
    private LocalDate createdDate;
    private LocalDate modifiedDate;
    private String modifiedBy;
    
    // Request/Response fields
    private Long compLeadId;
    private String applicationType; // 'INDIVIDUAL' or 'COMPANY'
    private String branchCode;
    private String branchName;
    private LocalDate applicationDate;
    private String status; // 'DRAFT','SUBMITTED','APPROVED','REJECTED'
    private String createdBy;
    private List<ApplicantIndividualDTO> applicantIndividuals;
    private List<ApplicantCompanyDTO> applicantCompanies;
    private List<FacilityRequestDTO> facilityRequests;
    private List<AssetDTO> assets;
    private List<LiabilityDTO> liabilities;
    private List<BankAccountDTO> bankAccounts;
    private List<IncomeTaxDTO> incomeTaxes;
    private List<InsuranceConsentDTO> insuranceConsents;
    
    // Constructors
    public ApplicationDTO() {
        this.applicantIndividuals = new ArrayList<>();
        this.applicantCompanies = new ArrayList<>();
        this.facilityRequests = new ArrayList<>();
        this.assets = new ArrayList<>();
        this.liabilities = new ArrayList<>();
        this.bankAccounts = new ArrayList<>();
        this.incomeTaxes = new ArrayList<>();
        this.insuranceConsents = new ArrayList<>();
    }
    
    // Getters and Setters
    public Long getApplicationId() {
        return applicationId;
    }
    
    public void setApplicationId(Long applicationId) {
        this.applicationId = applicationId;
    }
    
    public Long getCompLeadId() {
        return compLeadId;
    }
    
    public void setCompLeadId(Long compLeadId) {
        this.compLeadId = compLeadId;
    }
    
    public String getApplicationType() {
        return applicationType;
    }
    
    public void setApplicationType(String applicationType) {
        this.applicationType = applicationType;
    }
    
    public String getBranchCode() {
        return branchCode;
    }
    
    public void setBranchCode(String branchCode) {
        this.branchCode = branchCode;
    }
    
    public String getBranchName() {
        return branchName;
    }
    
    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }
    
    public LocalDate getApplicationDate() {
        return applicationDate;
    }
    
    public void setApplicationDate(LocalDate applicationDate) {
        this.applicationDate = applicationDate;
    }
    
    public String getStatus() {
        return status;
    }
    
    public void setStatus(String status) {
        this.status = status;
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
    
    public List<ApplicantIndividualDTO> getApplicantIndividuals() {
        return applicantIndividuals;
    }
    
    public void setApplicantIndividuals(List<ApplicantIndividualDTO> applicantIndividuals) {
        this.applicantIndividuals = applicantIndividuals;
    }
    
    public List<ApplicantCompanyDTO> getApplicantCompanies() {
        return applicantCompanies;
    }
    
    public void setApplicantCompanies(List<ApplicantCompanyDTO> applicantCompanies) {
        this.applicantCompanies = applicantCompanies;
    }
    
    public List<FacilityRequestDTO> getFacilityRequests() {
        return facilityRequests;
    }
    
    public void setFacilityRequests(List<FacilityRequestDTO> facilityRequests) {
        this.facilityRequests = facilityRequests;
    }
    
    public List<AssetDTO> getAssets() {
        return assets;
    }
    
    public void setAssets(List<AssetDTO> assets) {
        this.assets = assets;
    }
    
    public List<LiabilityDTO> getLiabilities() {
        return liabilities;
    }
    
    public void setLiabilities(List<LiabilityDTO> liabilities) {
        this.liabilities = liabilities;
    }
    
    public List<BankAccountDTO> getBankAccounts() {
        return bankAccounts;
    }
    
    public void setBankAccounts(List<BankAccountDTO> bankAccounts) {
        this.bankAccounts = bankAccounts;
    }
    
    public List<IncomeTaxDTO> getIncomeTaxes() {
        return incomeTaxes;
    }
    
    public void setIncomeTaxes(List<IncomeTaxDTO> incomeTaxes) {
        this.incomeTaxes = incomeTaxes;
    }
    
    public List<InsuranceConsentDTO> getInsuranceConsents() {
        return insuranceConsents;
    }
    
    public void setInsuranceConsents(List<InsuranceConsentDTO> insuranceConsents) {
        this.insuranceConsents = insuranceConsents;
    }
}
