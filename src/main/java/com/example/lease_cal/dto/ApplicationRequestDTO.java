package com.example.lease_cal.dto;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ApplicationRequestDTO {
    
    private Long compLeadId;
    private String applicationType; // 'INDIVIDUAL' or 'COMPANY'
    private String branchCode;
    private String branchName;
    private LocalDate applicationDate;
    private String status; // 'DRAFT','SUBMITTED','APPROVED','REJECTED'
    private String createdBy;
    private List<ApplicantIndividualRequestDTO> applicantIndividuals;
    private List<ApplicantCompanyRequestDTO> applicantCompanies;
    private List<FacilityRequestRequestDTO> facilityRequests;
    private List<AssetRequestDTO> assets;
    private List<LiabilityRequestDTO> liabilities;
    private List<BankAccountRequestDTO> bankAccounts;
    private List<IncomeTaxRequestDTO> incomeTaxes;
    private List<InsuranceConsentRequestDTO> insuranceConsents;
    
    // Constructors
    public ApplicationRequestDTO() {
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
    
    public String getCreatedBy() {
        return createdBy;
    }
    
    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }
    
    public List<ApplicantIndividualRequestDTO> getApplicantIndividuals() {
        return applicantIndividuals;
    }
    
    public void setApplicantIndividuals(List<ApplicantIndividualRequestDTO> applicantIndividuals) {
        this.applicantIndividuals = applicantIndividuals;
    }
    
    public List<ApplicantCompanyRequestDTO> getApplicantCompanies() {
        return applicantCompanies;
    }
    
    public void setApplicantCompanies(List<ApplicantCompanyRequestDTO> applicantCompanies) {
        this.applicantCompanies = applicantCompanies;
    }
    
    public List<FacilityRequestRequestDTO> getFacilityRequests() {
        return facilityRequests;
    }
    
    public void setFacilityRequests(List<FacilityRequestRequestDTO> facilityRequests) {
        this.facilityRequests = facilityRequests;
    }
    
    public List<AssetRequestDTO> getAssets() {
        return assets;
    }
    
    public void setAssets(List<AssetRequestDTO> assets) {
        this.assets = assets;
    }
    
    public List<LiabilityRequestDTO> getLiabilities() {
        return liabilities;
    }
    
    public void setLiabilities(List<LiabilityRequestDTO> liabilities) {
        this.liabilities = liabilities;
    }
    
    public List<BankAccountRequestDTO> getBankAccounts() {
        return bankAccounts;
    }
    
    public void setBankAccounts(List<BankAccountRequestDTO> bankAccounts) {
        this.bankAccounts = bankAccounts;
    }
    
    public List<IncomeTaxRequestDTO> getIncomeTaxes() {
        return incomeTaxes;
    }
    
    public void setIncomeTaxes(List<IncomeTaxRequestDTO> incomeTaxes) {
        this.incomeTaxes = incomeTaxes;
    }
    
    public List<InsuranceConsentRequestDTO> getInsuranceConsents() {
        return insuranceConsents;
    }
    
    public void setInsuranceConsents(List<InsuranceConsentRequestDTO> insuranceConsents) {
        this.insuranceConsents = insuranceConsents;
    }
}
