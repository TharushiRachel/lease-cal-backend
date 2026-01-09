package com.example.lease_cal.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "party_addresses")
public class PartyAddress {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "party_id", nullable = false)
    private Party party;
    
    @Column(name = "address", nullable = false, columnDefinition = "TEXT")
    private String address;
    
    // Constructors
    public PartyAddress() {
    }
    
    public PartyAddress(Party party, String address) {
        this.party = party;
        this.address = address;
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
    
    public String getAddress() {
        return address;
    }
    
    public void setAddress(String address) {
        this.address = address;
    }
}

