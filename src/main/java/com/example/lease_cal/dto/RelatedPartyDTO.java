package com.example.lease_cal.dto;

public class RelatedPartyDTO {
    
    private Long relatedPartyId;
    private Boolean isRelationShip;
    private Long compLeadId;
    private Long mainPartnerId;
    private Long relatedPartnerId;
    private String relationshipDescription;
    private Double sharePresentage;
    private String considerCrib;
    private String considerAdvanceAnalysis;
    
    // Constructors
    public RelatedPartyDTO() {
    }
    
    // Getters and Setters
    public Long getRelatedPartyId() {
        return relatedPartyId;
    }
    
    public void setRelatedPartyId(Long relatedPartyId) {
        this.relatedPartyId = relatedPartyId;
    }
    
    public Boolean getIsRelationShip() {
        return isRelationShip;
    }
    
    public void setIsRelationShip(Boolean isRelationShip) {
        this.isRelationShip = isRelationShip;
    }
    
    public Long getCompLeadId() {
        return compLeadId;
    }
    
    public void setCompLeadId(Long compLeadId) {
        this.compLeadId = compLeadId;
    }
    
    public Long getMainPartnerId() {
        return mainPartnerId;
    }
    
    public void setMainPartnerId(Long mainPartnerId) {
        this.mainPartnerId = mainPartnerId;
    }
    
    public Long getRelatedPartnerId() {
        return relatedPartnerId;
    }
    
    public void setRelatedPartnerId(Long relatedPartnerId) {
        this.relatedPartnerId = relatedPartnerId;
    }
    
    public String getRelationshipDescription() {
        return relationshipDescription;
    }
    
    public void setRelationshipDescription(String relationshipDescription) {
        this.relationshipDescription = relationshipDescription;
    }
    
    public Double getSharePresentage() {
        return sharePresentage;
    }
    
    public void setSharePresentage(Double sharePresentage) {
        this.sharePresentage = sharePresentage;
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
}

