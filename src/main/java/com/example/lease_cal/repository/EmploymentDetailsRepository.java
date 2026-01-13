package com.example.lease_cal.repository;

import com.example.lease_cal.entity.EmploymentDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmploymentDetailsRepository extends JpaRepository<EmploymentDetails, Long> {
}
