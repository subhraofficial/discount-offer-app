package com.example.discountoffer.dto;

public class CouponOfferDto {

    private Long id;
    private Integer discountPercentage;
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