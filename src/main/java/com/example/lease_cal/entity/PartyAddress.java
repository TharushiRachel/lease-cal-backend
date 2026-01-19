package com.example.lease_cal.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "t_comp_party_addresses")
public class PartyAddress {
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "party_address_seq")
    @SequenceGenerator(name = "party_address_seq", sequenceName = "seq_party_address_id", allocationSize = 1)
    @Column(name = "addresses_id")
    private Long addressesId;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "comp_party_id")
    private Party party;
    
    @Column(name = "address", length = 1000)
    private String address;

    @Column(name = "status", length = 20)
    private String status;

    @PrePersist
    protected void onCreate() {
        if (status == null) {
            status = "ACTIVE";
        }
    }
    
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}

