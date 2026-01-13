package com.example.lease_cal.repository;

import com.example.lease_cal.entity.FacilityRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FacilityRequestRepository extends JpaRepository<FacilityRequest, Long> {
}
