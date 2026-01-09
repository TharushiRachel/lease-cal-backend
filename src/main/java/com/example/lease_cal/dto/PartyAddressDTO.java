package com.example.lease_cal.dto;

public class PartyAddressDTO {
    
    private Long addressesId;
    private Long compPartyId;
    private String address;
    
    // Constructors
    public PartyAddressDTO() {
    }
    
    // Getters and Setters
    public Long getAddressesId() {
        return addressesId;
    }
    
    public void setAddressesId(Long addressesId) {
        this.addressesId = addressesId;
    }
    
    public Long getCompPartyId() {
        return compPartyId;
    }
    
    public void setCompPartyId(Long compPartyId) {
        this.compPartyId = compPartyId;
    }
    
    public String getAddress() {
        return address;
    }
    
    public void setAddress(String address) {
        this.address = address;
    }
}

