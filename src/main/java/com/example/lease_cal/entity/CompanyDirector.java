package com.example.lease_cal.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "company_director")
public class CompanyDirector {
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "company_director_seq")
    @SequenceGenerator(name = "company_director_seq", sequenceName = "seq_company_director_id", allocationSize = 1)
    @Column(name = "director_id")
    private Long directorId;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id")
    private ApplicantCompany applicantCompany;
    
    @Column(name = "name", length = 255)
    private String name;
    
    @Column(name = "address", length = 2000)
    private String address;
    
    @Column(name = "nic_no", length = 20)
    private String nicNo;
    
    @Column(name = "age")
    private Long age;
    
    @Column(name = "shareholding_percent", precision = 5, scale = 2)
    private BigDecimal shareholdingPercent;
    
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
    public CompanyDirector() {
    }
    
    // Getters and Setters
    public Long getDirectorId() {
        return directorId;
    }
    
    public void setDirectorId(Long directorId) {
        this.directorId = directorId;
    }
    
    public ApplicantCompany getApplicantCompany() {
        return applicantCompany;
    }
    
    public void setApplicantCompany(ApplicantCompany applicantCompany) {
        this.applicantCompany = applicantCompany;
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
