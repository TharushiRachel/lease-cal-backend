package com.example.lease_cal.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "t_comp_income_sources")
public class IncomeSource {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "income_source_id")
    private Long incomeSourceId;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "comp_party_id")
    private Party party;
    
    @Column(name = "income_type", nullable = false, length = 50)
    private String incomeType;
    
    @Column(name = "consider_for_repayment", length = 5)
    private String considerForRepayment;
    
    // Constructors
    public IncomeSource() {
    }
    
    public IncomeSource(Party party, String incomeType) {
        this.party = party;
        this.incomeType = incomeType;
    }
    
    // Getters and Setters
    public Long getIncomeSourceId() {
        return incomeSourceId;
    }
    
    public void setIncomeSourceId(Long incomeSourceId) {
        this.incomeSourceId = incomeSourceId;
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
    
    public String getConsiderForRepayment() {
        return considerForRepayment;
    }
    
    public void setConsiderForRepayment(String considerForRepayment) {
        this.considerForRepayment = considerForRepayment;
    }
}

