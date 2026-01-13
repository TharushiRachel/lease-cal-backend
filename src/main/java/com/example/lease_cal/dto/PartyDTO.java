package com.example.lease_cal.dto;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class PartyDTO {
    
    private Long compPartyId;
    private Long compLeadId;
    private String partyType;
    private String personalName;
    private String email;
    private String mobileNumber;
    private LocalDate dateOfBirth;
    private String civilStatus;
    private String preferredBranch;
    private Long accountNumber;
    private String considerCrib;
    private String considerAdvanceAnalysis;
    private LocalDate createdDate;
    private String createdBy;
    private LocalDate modifiedDate;
    private String modifiedBy;
    private List<PartyIdentificationDTO> identifications;
    private List<PartyAddressDTO> addresses;
    private List<IncomeSourceDTO> incomeSources;
    
    // Constructors
    public PartyDTO() {
        this.identifications = new ArrayList<>();
        this.addresses = new ArrayList<>();
        this.incomeSources = new ArrayList<>();
    }
    
    // Getters and Setters
    public Long getCompPartyId() {
        return compPartyId;
    }
    
    public void setCompPartyId(Long compPartyId) {
        this.compPartyId = compPartyId;
    }
    
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
    
    public List<PartyIdentificationDTO> getIdentifications() {
        return identifications;
    }
    
    public void setIdentifications(List<PartyIdentificationDTO> identifications) {
        this.identifications = identifications;
    }
    
    public List<PartyAddressDTO> getAddresses() {
        return addresses;
    }
    
    public void setAddresses(List<PartyAddressDTO> addresses) {
        this.addresses = addresses;
    }
    
    public List<IncomeSourceDTO> getIncomeSources() {
        return incomeSources;
    }
    
    public void setIncomeSources(List<IncomeSourceDTO> incomeSources) {
        this.incomeSources = incomeSources;
    }
}

