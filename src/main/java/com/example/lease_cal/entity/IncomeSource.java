package com.example.lease_cal.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "t_comp_income_sources")
public class IncomeSource {
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "income_source_seq")
    @SequenceGenerator(name = "income_source_seq", sequenceName = "seq_income_source_id", allocationSize = 1)
    @Column(name = "income_source_id")
    private Long incomeSourceId;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "comp_party_id")
    private Party party;
    
    @Column(name = "party", length = 255)
    private String partyName;
    
    @Column(name = "income_type", nullable = false, length = 50)
    private String incomeType;
    
    @Column(name = "consider_for_repayment", length = 5)
    private String considerForRepayment;

    @Column(name = "status", length = 20)
    private String status;

    @PrePersist
    protected void onCreate() {
        if (status == null) {
            status = "ACTIVE";
        }
    }
    
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
    
    public String getPartyName() {
        return partyName;
    }
    
    public void setPartyName(String partyName) {
        this.partyName = partyName;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}

