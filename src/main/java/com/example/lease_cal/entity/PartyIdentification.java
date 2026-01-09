package com.example.lease_cal.entity;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "t_comp_party_identifications")
public class PartyIdentification {
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "party_identification_seq")
    @SequenceGenerator(name = "party_identification_seq", sequenceName = "seq_party_identification_id", allocationSize = 1)
    @Column(name = "identification_id")
    private Long identificationId;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "comp_party_id")
    private Party party;
    
    @Column(name = "identification_type", nullable = false, length = 50)
    private String identificationType;
    
    @Column(name = "identification_number", nullable = false, length = 100)
    private String identificationNumber;
    
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
    public PartyIdentification() {
    }
    
    public PartyIdentification(Party party, String identificationType, String identificationNumber) {
        this.party = party;
        this.identificationType = identificationType;
        this.identificationNumber = identificationNumber;
    }
    
    // Getters and Setters
    public Long getIdentificationId() {
        return identificationId;
    }
    
    public void setIdentificationId(Long identificationId) {
        this.identificationId = identificationId;
    }
    
    public Party getParty() {
        return party;
    }
    
    public void setParty(Party party) {
        this.party = party;
    }
    
    public String getIdentificationType() {
        return identificationType;
    }
    
    public void setIdentificationType(String identificationType) {
        this.identificationType = identificationType;
    }
    
    public String getIdentificationNumber() {
        return identificationNumber;
    }
    
    public void setIdentificationNumber(String identificationNumber) {
        this.identificationNumber = identificationNumber;
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

