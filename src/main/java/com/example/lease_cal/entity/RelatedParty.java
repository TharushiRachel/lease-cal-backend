package com.example.lease_cal.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "related_parties")
public class RelatedParty {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "lead_id", nullable = false)
    private Lead lead;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "main_party_id", nullable = false)
    private Party mainParty;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "related_party_id", nullable = false)
    private Party relatedParty;
    
    @Column(name = "relationship_description", columnDefinition = "TEXT")
    private String relationshipDescription;
    
    @Column(name = "consider_crib", nullable = false)
    private Boolean considerCrib = false;
    
    @Column(name = "consider_advance_analysis", nullable = false)
    private Boolean considerAdvanceAnalysis = false;
    
    // Constructors
    public RelatedParty() {
    }
    
    public RelatedParty(Lead lead, Party mainParty, Party relatedParty) {
        this.lead = lead;
        this.mainParty = mainParty;
        this.relatedParty = relatedParty;
    }
    
    // Getters and Setters
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public Lead getLead() {
        return lead;
    }
    
    public void setLead(Lead lead) {
        this.lead = lead;
    }
    
    public Party getMainParty() {
        return mainParty;
    }
    
    public void setMainParty(Party mainParty) {
        this.mainParty = mainParty;
    }
    
    public Party getRelatedParty() {
        return relatedParty;
    }
    
    public void setRelatedParty(Party relatedParty) {
        this.relatedParty = relatedParty;
    }
    
    public String getRelationshipDescription() {
        return relationshipDescription;
    }
    
    public void setRelationshipDescription(String relationshipDescription) {
        this.relationshipDescription = relationshipDescription;
    }
    
    public Boolean getConsiderCrib() {
        return considerCrib;
    }
    
    public void setConsiderCrib(Boolean considerCrib) {
        this.considerCrib = considerCrib;
    }
    
    public Boolean getConsiderAdvanceAnalysis() {
        return considerAdvanceAnalysis;
    }
    
    public void setConsiderAdvanceAnalysis(Boolean considerAdvanceAnalysis) {
        this.considerAdvanceAnalysis = considerAdvanceAnalysis;
    }
}

