package com.example.lease_cal.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public class AssetDTO {
    
    private Long assetId;
    private Long applicationId;
    private String assetType;
    private String description;
    private String ownerName;
    private BigDecimal marketValue;
    private String mortgageDetails;
    private LocalDate createdDate;
    private String createdBy;
    private LocalDate modifiedDate;
    private String modifiedBy;
    
    // Constructors
    public AssetDTO() {
    }
    
    // Getters and Setters
    public Long getAssetId() {
        return assetId;
    }
    
    public void setAssetId(Long assetId) {
        this.assetId = assetId;
    }
    
    public Long getApplicationId() {
        return applicationId;
    }
    
    public void setApplicationId(Long applicationId) {
        this.applicationId = applicationId;
    }
    
    public String getAssetType() {
        return assetType;
    }
    
    public void setAssetType(String assetType) {
        this.assetType = assetType;
    }
    
    public String getDescription() {
        return description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    
    public String getOwnerName() {
        return ownerName;
    }
    
    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }
    
    public BigDecimal getMarketValue() {
        return marketValue;
    }
    
    public void setMarketValue(BigDecimal marketValue) {
        this.marketValue = marketValue;
    }
    
    public String getMortgageDetails() {
        return mortgageDetails;
    }
    
    public void setMortgageDetails(String mortgageDetails) {
        this.mortgageDetails = mortgageDetails;
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
