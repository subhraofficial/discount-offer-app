package com.example.discountoffer.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "offer_settings")
public class OfferSettings {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "discount_percentage")
    private Integer discountPercentage;

    @Column(name = "coupon_code")
    private String couponCode;

    public Long getId() {
        return id;
    }

    public Integer getDiscountPercentage() {
        return discountPercentage;
    }

    public String getCouponCode() {
        return couponCode;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setDiscountPercentage(Integer discountPercentage) {
        this.discountPercentage = discountPercentage;
    }

    public void setCouponCode(String couponCode) {
        this.couponCode = couponCode;
    }
}