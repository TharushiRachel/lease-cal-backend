package com.example.lease_cal.entity;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "applicant_individual")
public class ApplicantIndividual {
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "applicant_individual_seq")
    @SequenceGenerator(name = "applicant_individual_seq", sequenceName = "seq_applicant_individual_id", allocationSize = 1)
    @Column(name = "applicant_id")
    private Long applicantId;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "application_id")
    private Application application;
    
    @Column(name = "full_name", length = 255)
    private String fullName;
    
    @Column(name = "date_of_birth")
    private LocalDate dateOfBirth;
    
    @Column(name = "nic_passport_no", length = 50)
    private String nicPassportNo;
    
    @Column(name = "permanent_address", length = 2000)
    private String permanentAddress;
    
    @Column(name = "communication_address", length = 2000)
    private String communicationAddress;
    
    @Column(name = "residence_type", length = 20)
    private String residenceType; // 'OWN','RENTED','OTHER'
    
    @Column(name = "years_at_address")
    private Long yearsAtAddress;
    
    @Column(name = "nationality", length = 50)
    private String nationality;
    
    @Column(name = "is_pep", length = 1)
    private String isPep; // 'Y' or 'N'
    
    @Column(name = "pep_details", length = 2000)
    private String pepDetails;
    
    @Column(name = "civil_status", length = 50)
    private String civilStatus;
    
    @Column(name = "dependents_count")
    private Long dependentsCount;
    
    @Column(name = "highest_qualification", length = 255)
    private String highestQualification;
    
    @Column(name = "professional_qualification", length = 255)
    private String professionalQualification;
    
    @Column(name = "CREATED_DATE")
    private LocalDate createdDate;
    
    @Column(name = "CREATED_BY", length = 100)
    private String createdBy;
    
    @Column(name = "MODIFIED_DATE")
    private LocalDate modifiedDate;
    
    @Column(name = "MODIFIED_BY", length = 100)
    private String modifiedBy;
    
    @OneToMany(mappedBy = "applicantIndividual", cascade = CascadeType.ALL, orphanRemoval = true)
    private java.util.List<EmploymentDetails> employmentDetails = new java.util.ArrayList<>();
    
    @OneToMany(mappedBy = "applicantIndividual", cascade = CascadeType.ALL, orphanRemoval = true)
    private java.util.List<IncomeExpenditure> incomeExpenditures = new java.util.ArrayList<>();
    
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
    public ApplicantIndividual() {
    }
    
    // Getters and Setters
    public Long getApplicantId() {
        return applicantId;
    }
    
    public void setApplicantId(Long applicantId) {
        this.applicantId = applicantId;
    }
    
    public Application getApplication() {
        return application;
    }
    
    public void setApplication(Application application) {
        this.application = application;
    }
    
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
    
    public String getIsPep() {
        return isPep;
    }
    
    public void setIsPep(String isPep) {
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
    
    public java.util.List<EmploymentDetails> getEmploymentDetails() {
        return employmentDetails;
    }
    
    public void setEmploymentDetails(java.util.List<EmploymentDetails> employmentDetails) {
        this.employmentDetails = employmentDetails;
    }
    
    public java.util.List<IncomeExpenditure> getIncomeExpenditures() {
        return incomeExpenditures;
    }
    
    public void setIncomeExpenditures(java.util.List<IncomeExpenditure> incomeExpenditures) {
        this.incomeExpenditures = incomeExpenditures;
    }
}
