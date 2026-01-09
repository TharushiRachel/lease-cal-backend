package com.example.lease_cal.entity;

import com.example.lease_cal.entity.enums.PartyType;
import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "parties")
public class Party {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "lead_id", nullable = false)
    private Lead lead;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "party_type", nullable = false, length = 20)
    private PartyType partyType;
    
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
    
    @Column(name = "created_at")
    private LocalDateTime createdAt;
    
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
        createdAt = LocalDateTime.now();
    }
    
    // Constructors
    public Party() {
    }
    
    public Party(Lead lead, PartyType partyType, String personalName) {
        this.lead = lead;
        this.partyType = partyType;
        this.personalName = personalName;
    }
    
    // Getters and Setters
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public Lead getLead() {
        return lead;
    }
    
    public void setLead(Lead lead) {
        this.lead = lead;
    }
    
    public PartyType getPartyType() {
        return partyType;
    }
    
    public void setPartyType(PartyType partyType) {
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
    
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
    
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
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

