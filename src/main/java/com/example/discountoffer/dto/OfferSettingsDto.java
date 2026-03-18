package com.example.discountoffer.dto;

public class OfferSettingsDto {

    private Integer discountPercentage;
    private String couponCode;

    public Integer getDiscountPercentage() {
        return discountPercentage;
    }

    public String getCouponCode() {
        return couponCode;
    }

    public void setDiscountPercentage(Integer discountPercentage) {
        this.discountPercentage = discountPercentage;
    }

    public void setCouponCode(String couponCode) {
        this.couponCode = couponCode;
    }
}