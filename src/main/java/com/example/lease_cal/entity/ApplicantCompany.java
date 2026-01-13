package com.example.lease_cal.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "applicant_company")
public class ApplicantCompany {
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "applicant_company_seq")
    @SequenceGenerator(name = "applicant_company_seq", sequenceName = "seq_applicant_company_id", allocationSize = 1)
    @Column(name = "company_id")
    private Long companyId;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "application_id")
    private Application application;
    
    @Column(name = "name", length = 255)
    private String name;
    
    @Column(name = "address", length = 2000)
    private String address;
    
    @Column(name = "registration_no", length = 100)
    private String registrationNo;
    
    @Column(name = "incorporation_date")
    private LocalDate incorporationDate;
    
    @Column(name = "business_type", length = 255)
    private String businessType;
    
    @Column(name = "constitution", length = 50)
    private String constitution; // 'PRIVATE LTD','PUBLIC LTD','CORPORATION','OFF-SHORE'
    
    @Column(name = "no_of_employees")
    private Long noOfEmployees;
    
    @Column(name = "paid_up_capital", precision = 18, scale = 2)
    private BigDecimal paidUpCapital;
    
    @Column(name = "issued_capital", precision = 18, scale = 2)
    private BigDecimal issuedCapital;
    
    @Column(name = "CREATED_DATE")
    private LocalDate createdDate;
    
    @Column(name = "CREATED_BY", length = 100)
    private String createdBy;
    
    @Column(name = "MODIFIED_DATE")
    private LocalDate modifiedDate;
    
    @Column(name = "MODIFIED_BY", length = 100)
    private String modifiedBy;
    
    @OneToMany(mappedBy = "applicantCompany", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CompanyDirector> companyDirectors = new ArrayList<>();
    
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
    public ApplicantCompany() {
    }
    
    // Getters and Setters
    public Long getCompanyId() {
        return companyId;
    }
    
    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }
    
    public Application getApplication() {
        return application;
    }
    
    public void setApplication(Application application) {
        this.application = application;
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
    
    public List<CompanyDirector> getCompanyDirectors() {
        return companyDirectors;
    }
    
    public void setCompanyDirectors(List<CompanyDirector> companyDirectors) {
        this.companyDirectors = companyDirectors;
    }
}
