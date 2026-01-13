package com.example.lease_cal.repository;

import com.example.lease_cal.entity.Liability;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LiabilityRepository extends JpaRepository<Liability, Long> {
}
