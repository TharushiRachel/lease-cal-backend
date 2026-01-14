package com.example.lease_cal.repository;

import com.example.lease_cal.entity.ComprehensiveFacility;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ComprehensiveFacilityRepository extends JpaRepository<ComprehensiveFacility, Long> {
    
    /**
     * Find comprehensive facility by compFacilityId
     */
    Optional<ComprehensiveFacility> findByCompFacilityId(Long compFacilityId);
    
    /**
     * Find all facilities by comprehensive lead ID
     * 
     * @param leadId The ID of the comprehensive lead
     * @return List of facilities for the given lead
     */
    @Query("SELECT cf FROM ComprehensiveFacility cf WHERE cf.lead.compLeadId = :leadId")
    List<ComprehensiveFacility> findByLeadId(@Param("leadId") Long leadId);
}
