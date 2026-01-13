package com.example.lease_cal.repository;

import com.example.lease_cal.entity.ApplicantIndividual;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ApplicantIndividualRepository extends JpaRepository<ApplicantIndividual, Long> {
}
