package com.example.discountoffer.repository;

import com.example.discountoffer.entity.CouponOffer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CouponOfferRepository extends JpaRepository<CouponOffer, Long> {

    Optional<CouponOffer> findByCouponCodeIgnoreCase(String couponCode);
}