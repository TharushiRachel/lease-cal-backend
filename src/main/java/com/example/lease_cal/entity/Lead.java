package com.example.lease_cal.entity;

import com.example.lease_cal.entity.enums.CreationType;
import com.example.lease_cal.entity.enums.ProductType;
import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "leads")
public class Lead {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "lead_name", nullable = false, length = 255)
    private String leadName;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "creation_type", nullable = false, length = 20)
    private CreationType creationType;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "product_type", length = 20)
    private ProductType productType;
    
    @Column(name = "is_existing_customer", nullable = false)
    private Boolean isExistingCustomer = false;
    
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;
    
    @OneToMany(mappedBy = "lead", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Party> parties = new ArrayList<>();
    
    @OneToMany(mappedBy = "lead", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<RelatedParty> relatedParties = new ArrayList<>();
    
    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
    }
    
    // Constructors
    public Lead() {
    }
    
    public Lead(String leadName, CreationType creationType) {
        this.leadName = leadName;
        this.creationType = creationType;
    }
    
    // Getters and Setters
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public String getLeadName() {
        return leadName;
    }
    
    public void setLeadName(String leadName) {
        this.leadName = leadName;
    }
    
    public CreationType getCreationType() {
        return creationType;
    }
    
    public void setCreationType(CreationType creationType) {
        this.creationType = creationType;
    }
    
    public ProductType getProductType() {
        return productType;
    }
    
    public void setProductType(ProductType productType) {
        this.productType = productType;
    }
    
    public Boolean getIsExistingCustomer() {
        return isExistingCustomer;
    }
    
    public void setIsExistingCustomer(Boolean isExistingCustomer) {
        this.isExistingCustomer = isExistingCustomer;
    }
    
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
    
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
    
    public List<Party> getParties() {
        return parties;
    }
    
    public void setParties(List<Party> parties) {
        this.parties = parties;
    }
    
    public List<RelatedParty> getRelatedParties() {
        return relatedParties;
    }
    
    public void setRelatedParties(List<RelatedParty> relatedParties) {
        this.relatedParties = relatedParties;
    }
}

