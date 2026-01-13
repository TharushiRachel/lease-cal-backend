package com.example.lease_cal.dto;

import java.math.BigDecimal;

public class CompanyDirectorRequestDTO {
    
    private String name;
    private String address;
    private String nicNo;
    private Long age;
    private BigDecimal shareholdingPercent;
    private String createdBy;
    
    // Constructors
    public CompanyDirectorRequestDTO() {
    }
    
    // Getters and Setters
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getAddress() {
        return address;
    }
    
    public void setAddress(String address) {
        this.address = address;
    }
    
    public String getNicNo() {
        return nicNo;
    }
    
    public void setNicNo(String nicNo) {
        this.nicNo = nicNo;
    }
    
    public Long getAge() {
        return age;
    }
    
    public void setAge(Long age) {
        this.age = age;
    }
    
    public BigDecimal getShareholdingPercent() {
        return shareholdingPercent;
    }
    
    public void setShareholdingPercent(BigDecimal shareholdingPercent) {
        this.shareholdingPercent = shareholdingPercent;
    }
    
    public String getCreatedBy() {
        return createdBy;
    }
    
    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }
}
