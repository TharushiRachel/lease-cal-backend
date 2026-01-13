package com.example.lease_cal.repository;

import com.example.lease_cal.entity.CompanyDirector;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyDirectorRepository extends JpaRepository<CompanyDirector, Long> {
}
