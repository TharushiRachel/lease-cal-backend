package com.example.lease_cal.repository;

import com.example.lease_cal.entity.IncomeSource;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IncomeSourceRepository extends JpaRepository<IncomeSource, Long> {
    
    /**
     * Find all income sources by party ID
     * 
     * @param partyId The ID of the party
     * @return List of income sources for the given party
     */
    @Query("SELECT is FROM IncomeSource is WHERE is.party.compPartyId = :partyId")
    List<IncomeSource> findByPartyId(@Param("partyId") Long partyId);
}

