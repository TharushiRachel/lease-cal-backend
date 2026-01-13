package com.example.lease_cal.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "bank_accounts")
public class BankAccount {
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "bank_accounts_seq")
    @SequenceGenerator(name = "bank_accounts_seq", sequenceName = "seq_bank_accounts_id", allocationSize = 1)
    @Column(name = "bank_account_id")
    private Long bankAccountId;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "application_id")
    private Application application;
    
    @Column(name = "bank_name", length = 255)
    private String bankName;
    
    @Column(name = "branch_name", length = 255)
    private String branchName;
    
    @Column(name = "account_no", length = 50)
    private String accountNo;
    
    @Column(name = "account_type", length = 50)
    private String accountType;
    
    @Column(name = "date_opened")
    private LocalDate dateOpened;
    
    @Column(name = "balance", precision = 18, scale = 2)
    private BigDecimal balance;
    
    @Column(name = "holder_name", length = 255)
    private String holderName;
    
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
    public BankAccount() {
    }
    
    // Getters and Setters
    public Long getBankAccountId() {
        return bankAccountId;
    }
    
    public void setBankAccountId(Long bankAccountId) {
        this.bankAccountId = bankAccountId;
    }
    
    public Application getApplication() {
        return application;
    }
    
    public void setApplication(Application application) {
        this.application = application;
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
