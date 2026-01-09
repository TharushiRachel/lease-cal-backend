package com.example.lease_cal.entity;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "t_comp_lead")
public class Lead {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comp_lead_id")
    private Long compLeadId;
    
    @Column(name = "lead_name", nullable = false, length = 255)
    private String leadName;
    
    @Column(name = "creation_type", length = 50)
    private String creationType;
    
    @Column(name = "CREATED_DATE")
    private LocalDate createdDate;
    
    @Column(name = "CREATED_BY", length = 100)
    private String createdBy;
    
    @Column(name = "MODIFIED_DATE")
    private LocalDate modifiedDate;
    
    @Column(name = "MODIFIED_BY", length = 100)
    private String modifiedBy;
    
    @OneToMany(mappedBy = "lead", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Party> parties = new ArrayList<>();
    
    @OneToMany(mappedBy = "lead", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<RelatedParty> relatedParties = new ArrayList<>();
    
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
    public Lead() {
    }
    
    public Lead(String leadName) {
        this.leadName = leadName;
    }
    
    // Getters and Setters
    public Long getCompLeadId() {
        return compLeadId;
    }
    
    public void setCompLeadId(Long compLeadId) {
        this.compLeadId = compLeadId;
    }
    
    public String getLeadName() {
        return leadName;
    }
    
    public void setLeadName(String leadName) {
        this.leadName = leadName;
    }
    
    public String getCreationType() {
        return creationType;
    }
    
    public void setCreationType(String creationType) {
        this.creationType = creationType;
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
    
    public List<Party> getParties() {
        return parties;
    }
    
    public void setParties(List<Party> parties) {
        this.parties = parties;
    }
    
    public List<RelatedParty> getRelatedParties() {
        return relatedParties;
    }
    
    public void setRelatedParties(List<RelatedParty> relatedParties) {
        this.relatedParties = relatedParties;
    }
}

