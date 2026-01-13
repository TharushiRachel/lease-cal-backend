package com.example.lease_cal.dto;

import java.math.BigDecimal;

public class AssetRequestDTO {
    
    private String assetType; // 'LAND_BUILDING','VEHICLE','SHARE_TBILL','INSURANCE','OTHER'
    private String description;
    private String ownerName;
    private BigDecimal marketValue;
    private String mortgageDetails;
    private String createdBy;
    
    // Constructors
    public AssetRequestDTO() {
    }
    
    // Getters and Setters
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
    
    public String getCreatedBy() {
        return createdBy;
    }
    
    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }
}
