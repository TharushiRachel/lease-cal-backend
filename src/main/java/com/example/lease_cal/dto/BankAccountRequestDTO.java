package com.example.lease_cal.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public class BankAccountRequestDTO {
    
    private String bankName;
    private String branchName;
    private String accountNo;
    private String accountType;
    private LocalDate dateOpened;
    private BigDecimal balance;
    private String holderName;
    private String createdBy;
    
    // Constructors
    public BankAccountRequestDTO() {
    }
    
    // Getters and Setters
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
    
    public String getCreatedBy() {
        return createdBy;
    }
    
    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }
}
