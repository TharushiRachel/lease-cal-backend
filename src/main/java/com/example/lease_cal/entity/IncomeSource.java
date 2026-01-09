package com.example.lease_cal.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "income_sources")
public class IncomeSource {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "party_id", nullable = false)
    private Party party;
    
    @Column(name = "income_type", nullable = false, length = 50)
    private String incomeType;
    
    @Column(name = "consider_for_repayment", nullable = false)
    private Boolean considerForRepayment = false;
    
    // Constructors
    public IncomeSource() {
    }
    
    public IncomeSource(Party party, String incomeType) {
        this.party = party;
        this.incomeType = incomeType;
    }
    
    // Getters and Setters
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public Party getParty() {
        return party;
    }
    
    public void setParty(Party party) {
        this.party = party;
    }
    
    public String getIncomeType() {
        return incomeType;
    }
    
    public void setIncomeType(String incomeType) {
        this.incomeType = incomeType;
    }
    
    public Boolean getConsiderForRepayment() {
        return considerForRepayment;
    }
    
    public void setConsiderForRepayment(Boolean considerForRepayment) {
        this.considerForRepayment = considerForRepayment;
    }
}

