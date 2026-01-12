package com.example.lease_cal.dto;

import java.util.ArrayList;
import java.util.List;

public class IncomeSourceListRequestDTO {
    
    private Long partyId;
    private List<IncomeSourceRequestDTO> incomeSources;
    
    // Constructors
    public IncomeSourceListRequestDTO() {
        this.incomeSources = new ArrayList<>();
    }
    
    // Getters and Setters
    public Long getPartyId() {
        return partyId;
    }
    
    public void setPartyId(Long partyId) {
        this.partyId = partyId;
    }
    
    public List<IncomeSourceRequestDTO> getIncomeSources() {
        return incomeSources;
    }
    
    public void setIncomeSources(List<IncomeSourceRequestDTO> incomeSources) {
        this.incomeSources = incomeSources;
    }
}

