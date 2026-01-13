package com.example.lease_cal.entity;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "employment_details")
public class EmploymentDetails {
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "employment_details_seq")
    @SequenceGenerator(name = "employment_details_seq", sequenceName = "seq_employment_details_id", allocationSize = 1)
    @Column(name = "employment_id")
    private Long employmentId;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "applicant_id")
    private ApplicantIndividual applicantIndividual;
    
    @Column(name = "employer_name", length = 255)
    private String employerName;
    
    @Column(name = "employment_type", length = 50)
    private String employmentType;
    
    @Column(name = "designation", length = 255)
    private String designation;
    
    @Column(name = "years_with_employer")
    private Long yearsWithEmployer;
    
    @Column(name = "business_nature", length = 255)
    private String businessNature;
    
    @Column(name = "employer_address", length = 2000)
    private String employerAddress;
    
    @Column(name = "office_phone", length = 50)
    private String officePhone;
    
    @Column(name = "avg_income_growth", length = 50)
    private String avgIncomeGrowth; // '>10%','>5%','<=5%','POSITIVE_ONE_YEAR','NEGATIVE_BOTH'
    
    @Column(name = "CREATED_DATE")
    private LocalDate createdDate;
    
    @Column(name = "CREATED_BY", length = 100)
    private String createdBy;
    
    @Column(name = "MODIFIED_DATE")
    private LocalDate modifiedDate;
    
    @Column(name = "MODIFIED_BY", length = 100)
    private String modifiedBy;
    
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
    public EmploymentDetails() {
    }
    
    // Getters and Setters
    public Long getEmploymentId() {
        return employmentId;
    }
    
    public void setEmploymentId(Long employmentId) {
        this.employmentId = employmentId;
    }
    
    public ApplicantIndividual getApplicantIndividual() {
        return applicantIndividual;
    }
    
    public void setApplicantIndividual(ApplicantIndividual applicantIndividual) {
        this.applicantIndividual = applicantIndividual;
    }
    
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
