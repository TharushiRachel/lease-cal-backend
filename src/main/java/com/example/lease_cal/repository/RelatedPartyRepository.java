package com.example.lease_cal.repository;

import com.example.lease_cal.entity.RelatedParty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RelatedPartyRepository extends JpaRepository<RelatedParty, Long> {
}

