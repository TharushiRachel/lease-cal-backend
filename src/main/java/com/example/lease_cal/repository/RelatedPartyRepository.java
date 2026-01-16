package com.example.lease_cal.repository;

import com.example.lease_cal.entity.RelatedParty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RelatedPartyRepository extends JpaRepository<RelatedParty, Long> {
    
    /**
     * Find all related parties by lead ID
     * 
     * @param leadId The ID of the comprehensive lead
     * @return List of related parties for the given lead
     */
    @Query("SELECT rp FROM RelatedParty rp WHERE rp.lead.compLeadId = :leadId")
    List<RelatedParty> findByLeadId(@Param("leadId") Long leadId);
}

