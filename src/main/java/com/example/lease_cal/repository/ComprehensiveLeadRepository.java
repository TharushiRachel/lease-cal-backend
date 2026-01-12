package com.example.lease_cal.repository;

import com.example.lease_cal.entity.ComprehensiveLead;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ComprehensiveLeadRepository extends JpaRepository<ComprehensiveLead, Long> {
}
