package com.example.lease_cal.repository;

import com.example.lease_cal.entity.ComprehensiveLead;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ComprehensiveLeadRepository extends JpaRepository<ComprehensiveLead, Long> {
    
    /**
     * Find comprehensive lead by compLeadId (using Spring Data JPA method name query)
     * This is equivalent to findById but uses the actual field name
     */
    Optional<ComprehensiveLead> findByCompLeadId(Long compLeadId);
    
    /**
     * Find comprehensive lead by ID with all child entities loaded
     * Uses JOIN FETCH to eagerly load parties, related parties, and their nested entities
     * JPQL uses entity class names (ComprehensiveLead, Party, etc.) which JPA maps to table names
     */
    @Query("SELECT DISTINCT cl FROM ComprehensiveLead cl " +
           "LEFT JOIN FETCH cl.parties p " +
           "LEFT JOIN FETCH p.identifications " +
           "LEFT JOIN FETCH p.addresses " +
           "LEFT JOIN FETCH p.incomeSources " +
           "LEFT JOIN FETCH cl.relatedParties rp " +
           "WHERE cl.compLeadId = :leadId")
    Optional<ComprehensiveLead> findByIdWithAllRelations(@Param("leadId") Long leadId);
}
