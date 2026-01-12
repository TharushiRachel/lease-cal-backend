package com.example.lease_cal.entity;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "t_comp_parties")
public class Party {
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "comp_party_seq")
    @SequenceGenerator(name = "comp_party_seq", sequenceName = "seq_comp_party_id", allocationSize = 1)
    @Column(name = "comp_party_id")
    private Long compPartyId;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "comp_lead_id")
    private Lead lead;
    
    @Column(name = "party_type", length = 20)
    private String partyType;
    
    @Column(name = "personal_name", nullable = false, length = 255)
    private String personalName;
    
    @Column(name = "email", length = 255)
    private String email;
    
    @Column(name = "mobile_number", length = 20)
    private String mobileNumber;
    
    @Column(name = "date_of_birth")
    private LocalDate dateOfBirth;
    
    @Column(name = "civil_status", length = 20)
    private String civilStatus;
    
    @Column(name = "preferred_branch", length = 100)
    private String preferredBranch;
    
    @Column(name = "consider_crib", length = 5)
    private String considerCrib;
    
    @Column(name = "consider_advance_analysis", length = 5)
    private String considerAdvanceAnalysis;
    
    @Column(name = "CREATED_DATE")
    private LocalDate createdDate;
    
    @Column(name = "CREATED_BY", length = 100)
    private String createdBy;
    
    @Column(name = "MODIFIED_DATE")
    private LocalDate modifiedDate;
    
    @Column(name = "MODIFIED_BY", length = 100)
    private String modifiedBy;
    
    @OneToMany(mappedBy = "party", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PartyIdentification> identifications = new ArrayList<>();
    
    @OneToMany(mappedBy = "party", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PartyAddress> addresses = new ArrayList<>();
    
    @OneToMany(mappedBy = "mainParty", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<RelatedParty> mainPartyRelations = new ArrayList<>();
    
    @OneToMany(mappedBy = "relatedParty", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<RelatedParty> relatedPartyRelations = new ArrayList<>();
    
    @OneToMany(mappedBy = "party", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<IncomeSource> incomeSources = new ArrayList<>();
    
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
    public Party() {
    }
    
    public Party(Lead lead, String partyType, String personalName) {
        this.lead = lead;
        this.partyType = partyType;
        this.personalName = personalName;
    }
    
    // Getters and Setters
    public Long getCompPartyId() {
        return compPartyId;
    }
    
    public void setCompPartyId(Long compPartyId) {
        this.compPartyId = compPartyId;
    }
    
    public Lead getLead() {
        return lead;
    }
    
    public void setLead(Lead lead) {
        this.lead = lead;
    }
    
    public String getPartyType() {
        return partyType;
    }
    
    public void setPartyType(String partyType) {
        this.partyType = partyType;
    }
    
    public String getPersonalName() {
        return personalName;
    }
    
    public void setPersonalName(String personalName) {
        this.personalName = personalName;
    }
    
    public String getEmail() {
        return email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    public String getMobileNumber() {
        return mobileNumber;
    }
    
    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }
    
    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }
    
    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }
    
    public String getCivilStatus() {
        return civilStatus;
    }
    
    public void setCivilStatus(String civilStatus) {
        this.civilStatus = civilStatus;
    }
    
    public String getPreferredBranch() {
        return preferredBranch;
    }
    
    public void setPreferredBranch(String preferredBranch) {
        this.preferredBranch = preferredBranch;
    }
    
    public String getConsiderCrib() {
        return considerCrib;
    }
    
    public void setConsiderCrib(String considerCrib) {
        this.considerCrib = considerCrib;
    }
    
    public String getConsiderAdvanceAnalysis() {
        return considerAdvanceAnalysis;
    }
    
    public void setConsiderAdvanceAnalysis(String considerAdvanceAnalysis) {
        this.considerAdvanceAnalysis = considerAdvanceAnalysis;
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
    
    public List<PartyIdentification> getIdentifications() {
        return identifications;
    }
    
    public void setIdentifications(List<PartyIdentification> identifications) {
        this.identifications = identifications;
    }
    
    public List<PartyAddress> getAddresses() {
        return addresses;
    }
    
    public void setAddresses(List<PartyAddress> addresses) {
        this.addresses = addresses;
    }
    
    public List<RelatedParty> getMainPartyRelations() {
        return mainPartyRelations;
    }
    
    public void setMainPartyRelations(List<RelatedParty> mainPartyRelations) {
        this.mainPartyRelations = mainPartyRelations;
    }
    
    public List<RelatedParty> getRelatedPartyRelations() {
        return relatedPartyRelations;
    }
    
    public void setRelatedPartyRelations(List<RelatedParty> relatedPartyRelations) {
        this.relatedPartyRelations = relatedPartyRelations;
    }
    
    public List<IncomeSource> getIncomeSources() {
        return incomeSources;
    }
    
    public void setIncomeSources(List<IncomeSource> incomeSources) {
        this.incomeSources = incomeSources;
    }
}

