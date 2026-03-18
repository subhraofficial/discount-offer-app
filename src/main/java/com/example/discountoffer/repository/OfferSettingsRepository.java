package com.example.discountoffer.repository;

import com.example.discountoffer.entity.OfferSettings;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OfferSettingsRepository extends JpaRepository<OfferSettings, Long> {
}