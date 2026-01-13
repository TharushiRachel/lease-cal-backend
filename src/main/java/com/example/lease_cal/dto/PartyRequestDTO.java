package com.example.lease_cal.dto;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class PartyRequestDTO {
    
    private Long compLeadId;
    private String creationType;
    private String partyType;
    private String personalName;
    private String email;
    private String mobileNumber;
    private LocalDate dateOfBirth;
    private String civilStatus;
    private String preferredBranch;
    private Long accountNumber;
    private Boolean considerCrib;
    private Boolean considerAA;
    private String createdBy;
    private List<PartyIdentificationRequestDTO> identifications;
    private List<PartyAddressRequestDTO> addresses;
    private List<IncomeSourceRequestDTO> incomeSources;
    
    // Constructors
    public PartyRequestDTO() {
        this.identifications = new ArrayList<>();
        this.addresses = new ArrayList<>();
        this.incomeSources = new ArrayList<>();
    }
    
    // Getters and Setters
    public Long getCompLeadId() {
        return compLeadId;
    }
    
    public void setCompLeadId(Long compLeadId) {
        this.compLeadId = compLeadId;
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
    
    public String getCreationType() {
        return creationType;
    }
    
    public void setCreationType(String creationType) {
        this.creationType = creationType;
    }
    
    public String getPreferredBranch() {
        return preferredBranch;
    }
    
    public void setPreferredBranch(String preferredBranch) {
        this.preferredBranch = preferredBranch;
    }
    
    public Long getAccountNumber() {
        return accountNumber;
    }
    
    public void setAccountNumber(Long accountNumber) {
        this.accountNumber = accountNumber;
    }
    
    public Boolean getConsiderCrib() {
        return considerCrib;
    }
    
    public void setConsiderCrib(Boolean considerCrib) {
        this.considerCrib = considerCrib;
    }
    
    public Boolean getConsiderAA() {
        return considerAA;
    }
    
    public void setConsiderAA(Boolean considerAA) {
        this.considerAA = considerAA;
    }
    
    public String getCreatedBy() {
        return createdBy;
    }
    
    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }
    
    public List<PartyIdentificationRequestDTO> getIdentifications() {
        return identifications;
    }
    
    public void setIdentifications(List<PartyIdentificationRequestDTO> identifications) {
        this.identifications = identifications;
    }
    
    public List<PartyAddressRequestDTO> getAddresses() {
        return addresses;
    }
    
    public void setAddresses(List<PartyAddressRequestDTO> addresses) {
        this.addresses = addresses;
    }
    
    public List<IncomeSourceRequestDTO> getIncomeSources() {
        return incomeSources;
    }
    
    public void setIncomeSources(List<IncomeSourceRequestDTO> incomeSources) {
        this.incomeSources = incomeSources;
    }
}

