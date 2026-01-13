package com.example.lease_cal.entity;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "application")
public class Application {
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "application_seq")
    @SequenceGenerator(name = "application_seq", sequenceName = "seq_application_id", allocationSize = 1)
    @Column(name = "application_id")
    private Long applicationId;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "comp_lead_id")
    private ComprehensiveLead comprehensiveLead;
    
    @Column(name = "application_type", nullable = false, length = 20)
    private String applicationType; // 'INDIVIDUAL' or 'COMPANY'
    
    @Column(name = "branch_code", length = 50)
    private String branchCode;
    
    @Column(name = "branch_name", length = 255)
    private String branchName;
    
    @Column(name = "application_date")
    private LocalDate applicationDate;
    
    @Column(name = "status", length = 20)
    private String status; // 'DRAFT','SUBMITTED','APPROVED','REJECTED'
    
    @Column(name = "CREATED_DATE")
    private LocalDate createdDate;
    
    @Column(name = "CREATED_BY", length = 100)
    private String createdBy;
    
    @Column(name = "MODIFIED_DATE")
    private LocalDate modifiedDate;
    
    @Column(name = "MODIFIED_BY", length = 100)
    private String modifiedBy;
    
    @OneToMany(mappedBy = "application", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ApplicantIndividual> applicantIndividuals = new ArrayList<>();
    
    @OneToMany(mappedBy = "application", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ApplicantCompany> applicantCompanies = new ArrayList<>();
    
    @OneToMany(mappedBy = "application", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<FacilityRequest> facilityRequests = new ArrayList<>();
    
    @OneToMany(mappedBy = "application", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Asset> assets = new ArrayList<>();
    
    @OneToMany(mappedBy = "application", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Liability> liabilities = new ArrayList<>();
    
    @OneToMany(mappedBy = "application", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<BankAccount> bankAccounts = new ArrayList<>();
    
    @OneToMany(mappedBy = "application", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<IncomeTax> incomeTaxes = new ArrayList<>();
    
    @OneToMany(mappedBy = "application", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<InsuranceConsent> insuranceConsents = new ArrayList<>();
    
    @PrePersist
    protected void onCreate() {
        if (createdDate == null) {
            createdDate = LocalDate.now();
        }
        if (status == null) {
            status = "DRAFT";
        }
    }
    
    @PreUpdate
    protected void onUpdate() {
        modifiedDate = LocalDate.now();
    }
    
    // Constructors
    public Application() {
    }
    
    public Application(ComprehensiveLead comprehensiveLead, String applicationType) {
        this.comprehensiveLead = comprehensiveLead;
        this.applicationType = applicationType;
    }
    
    // Getters and Setters
    public Long getApplicationId() {
        return applicationId;
    }
    
    public void setApplicationId(Long applicationId) {
        this.applicationId = applicationId;
    }
    
    public ComprehensiveLead getComprehensiveLead() {
        return comprehensiveLead;
    }
    
    public void setComprehensiveLead(ComprehensiveLead comprehensiveLead) {
        this.comprehensiveLead = comprehensiveLead;
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
    
    public List<ApplicantIndividual> getApplicantIndividuals() {
        return applicantIndividuals;
    }
    
    public void setApplicantIndividuals(List<ApplicantIndividual> applicantIndividuals) {
        this.applicantIndividuals = applicantIndividuals;
    }
    
    public List<ApplicantCompany> getApplicantCompanies() {
        return applicantCompanies;
    }
    
    public void setApplicantCompanies(List<ApplicantCompany> applicantCompanies) {
        this.applicantCompanies = applicantCompanies;
    }
    
    public List<FacilityRequest> getFacilityRequests() {
        return facilityRequests;
    }
    
    public void setFacilityRequests(List<FacilityRequest> facilityRequests) {
        this.facilityRequests = facilityRequests;
    }
    
    public List<Asset> getAssets() {
        return assets;
    }
    
    public void setAssets(List<Asset> assets) {
        this.assets = assets;
    }
    
    public List<Liability> getLiabilities() {
        return liabilities;
    }
    
    public void setLiabilities(List<Liability> liabilities) {
        this.liabilities = liabilities;
    }
    
    public List<BankAccount> getBankAccounts() {
        return bankAccounts;
    }
    
    public void setBankAccounts(List<BankAccount> bankAccounts) {
        this.bankAccounts = bankAccounts;
    }
    
    public List<IncomeTax> getIncomeTaxes() {
        return incomeTaxes;
    }
    
    public void setIncomeTaxes(List<IncomeTax> incomeTaxes) {
        this.incomeTaxes = incomeTaxes;
    }
    
    public List<InsuranceConsent> getInsuranceConsents() {
        return insuranceConsents;
    }
    
    public void setInsuranceConsents(List<InsuranceConsent> insuranceConsents) {
        this.insuranceConsents = insuranceConsents;
    }
}
