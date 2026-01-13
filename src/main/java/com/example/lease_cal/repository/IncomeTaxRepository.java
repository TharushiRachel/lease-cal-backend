package com.example.lease_cal.repository;

import com.example.lease_cal.entity.IncomeTax;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IncomeTaxRepository extends JpaRepository<IncomeTax, Long> {
}
