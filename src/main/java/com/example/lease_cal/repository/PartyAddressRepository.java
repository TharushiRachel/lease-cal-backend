package com.example.lease_cal.repository;

import com.example.lease_cal.entity.PartyAddress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PartyAddressRepository extends JpaRepository<PartyAddress, Long> {
}
