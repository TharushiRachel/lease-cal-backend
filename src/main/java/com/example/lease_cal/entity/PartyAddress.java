package com.example.lease_cal.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "t_comp_party_addresses")
public class PartyAddress {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "addresses_id")
    private Long addressesId;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "comp_party_id")
    private Party party;
    
    @Column(name = "address", length = 1000)
    private String address;
    
    // Constructors
    public PartyAddress() {
    }
    
    public PartyAddress(Party party, String address) {
        this.party = party;
        this.address = address;
    }
    
    // Getters and Setters
    public Long getAddressesId() {
        return addressesId;
    }
    
    public void setAddressesId(Long addressesId) {
        this.addressesId = addressesId;
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

