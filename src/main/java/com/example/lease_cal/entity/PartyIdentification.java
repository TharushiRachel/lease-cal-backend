package com.example.lease_cal.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "party_identifications")
public class PartyIdentification {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "party_id", nullable = false)
    private Party party;
    
    @Column(name = "identification_type", nullable = false, length = 50)
    private String identificationType;
    
    @Column(name = "identification_number", nullable = false, length = 100)
    private String identificationNumber;
    
    // Constructors
    public PartyIdentification() {
    }
    
    public PartyIdentification(Party party, String identificationType, String identificationNumber) {
        this.party = party;
        this.identificationType = identificationType;
        this.identificationNumber = identificationNumber;
    }
    
    // Getters and Setters
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
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
}

