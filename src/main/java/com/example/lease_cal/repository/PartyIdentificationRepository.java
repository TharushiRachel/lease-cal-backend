package com.example.lease_cal.repository;

import com.example.lease_cal.entity.PartyIdentification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PartyIdentificationRepository extends JpaRepository<PartyIdentification, Long> {
}
