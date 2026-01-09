package com.example.lease_cal.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "t_comp_related_parties")
public class RelatedParty {
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "related_party_seq")
    @SequenceGenerator(name = "related_party_seq", sequenceName = "seq_related_party_id", allocationSize = 1)
    @Column(name = "related_party_id")
    private Long relatedPartyId;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "comp_lead_id")
    private Lead lead;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "main_partner_id")
    private Party mainParty;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "related_partner_id")
    private Party relatedParty;
    
    @Column(name = "relationship_description", length = 500)
    private String relationshipDescription;
    
    @Column(name = "consider_crib", length = 5)
    private String considerCrib;
    
    @Column(name = "consider_advance_analysis", length = 5)
    private String considerAdvanceAnalysis;
    
    // Constructors
    public RelatedParty() {
    }
    
    public RelatedParty(Lead lead, Party mainParty, Party relatedParty) {
        this.lead = lead;
        this.mainParty = mainParty;
        this.relatedParty = relatedParty;
    }
    
    // Getters and Setters
    public Long getRelatedPartyId() {
        return relatedPartyId;
    }
    
    public void setRelatedPartyId(Long relatedPartyId) {
        this.relatedPartyId = relatedPartyId;
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

