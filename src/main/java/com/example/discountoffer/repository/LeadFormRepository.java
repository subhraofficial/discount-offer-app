package com.example.discountoffer.repository;

import com.example.discountoffer.entity.LeadForm;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface LeadFormRepository extends JpaRepository<LeadForm, Long> {

    List<LeadForm> findByCreatedAtBetween(LocalDateTime start, LocalDateTime end);

    List<LeadForm> findByPurchaseStatusIgnoreCase(String purchaseStatus);

    List<LeadForm> findByCreatedAtBetweenAndPurchaseStatusIgnoreCase(
            LocalDateTime start,
            LocalDateTime end,
            String purchaseStatus
    );

    Optional<LeadForm> findByPhoneNumber(String phoneNumber);
}