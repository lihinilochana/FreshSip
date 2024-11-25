package com.freshsip.freshsip.repository;

import com.freshsip.freshsip.entity.Billing;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BillingRepo extends JpaRepository<Billing, Integer> {
}