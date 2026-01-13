package com.example.lease_cal.repository;

import com.example.lease_cal.entity.ApplicantCompany;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ApplicantCompanyRepository extends JpaRepository<ApplicantCompany, Long> {
}
