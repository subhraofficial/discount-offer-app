package com.example.discountoffer.repository;

import com.example.discountoffer.entity.CouponOffer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CouponOfferRepository extends JpaRepository<CouponOffer, Long> {
}