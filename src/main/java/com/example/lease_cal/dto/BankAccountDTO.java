package com.example.lease_cal.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public class BankAccountDTO {
    
    private Long bankAccountId;
    private Long applicationId;
    private String bankName;
    private String branchName;
    private String accountNo;
    private String accountType;
    private LocalDate dateOpened;
    private BigDecimal balance;
    private String holderName;
    private LocalDate createdDate;
    private String createdBy;
    private LocalDate modifiedDate;
    private String modifiedBy;
    
    // Constructors
    public BankAccountDTO() {
    }
    
    // Getters and Setters
    public Long getBankAccountId() {
        return bankAccountId;
    }
    
    public void setBankAccountId(Long bankAccountId) {
        this.bankAccountId = bankAccountId;
    }
    
    public Long getApplicationId() {
        return applicationId;
    }
    
    public void setApplicationId(Long applicationId) {
        this.applicationId = applicationId;
    }
    
    public String getBankName() {
        return bankName;
    }
    
    public void setBankName(String bankName) {
        this.bankName = bankName;
    }
    
    public String getBranchName() {
        return branchName;
    }
    
    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }
    
    public String getAccountNo() {
        return accountNo;
    }
    
    public void setAccountNo(String accountNo) {
        this.accountNo = accountNo;
    }
    
    public String getAccountType() {
        return accountType;
    }
    
    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }
    
    public LocalDate getDateOpened() {
        return dateOpened;
    }
    
    public void setDateOpened(LocalDate dateOpened) {
        this.dateOpened = dateOpened;
    }
    
    public BigDecimal getBalance() {
        return balance;
    }
    
    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }
    
    public String getHolderName() {
        return holderName;
    }
    
    public void setHolderName(String holderName) {
        this.holderName = holderName;
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
