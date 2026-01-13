package com.example.lease_cal.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "assets")
public class Asset {
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "assets_seq")
    @SequenceGenerator(name = "assets_seq", sequenceName = "seq_assets_id", allocationSize = 1)
    @Column(name = "asset_id")
    private Long assetId;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "application_id")
    private Application application;
    
    @Column(name = "asset_type", length = 50)
    private String assetType; // 'LAND_BUILDING','VEHICLE','SHARE_TBILL','INSURANCE','OTHER'
    
    @Column(name = "description", length = 2000)
    private String description;
    
    @Column(name = "owner_name", length = 255)
    private String ownerName;
    
    @Column(name = "market_value", precision = 18, scale = 2)
    private BigDecimal marketValue;
    
    @Column(name = "mortgage_details", length = 2000)
    private String mortgageDetails;
    
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
    public Asset() {
    }
    
    // Getters and Setters
    public Long getAssetId() {
        return assetId;
    }
    
    public void setAssetId(Long assetId) {
        this.assetId = assetId;
    }
    
    public Application getApplication() {
        return application;
    }
    
    public void setApplication(Application application) {
        this.application = application;
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
