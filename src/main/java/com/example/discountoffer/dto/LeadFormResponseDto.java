package com.example.discountoffer.dto;

import java.time.LocalDateTime;

public class LeadFormResponseDto {

    private Long id;
    private String name;
    private String className;
    private String phoneNumber;
    private String purchaseStatus;
    private Integer discountPercentage;
    private String couponCode;
    private LocalDateTime createdAt;

    public LeadFormResponseDto() {
    }

    public LeadFormResponseDto(Long id, String name, String className, String phoneNumber,
                               String purchaseStatus, Integer discountPercentage,
                               String couponCode, LocalDateTime createdAt) {
        this.id = id;
        this.name = name;
        this.className = className;
        this.phoneNumber = phoneNumber;
        this.purchaseStatus = purchaseStatus;
        this.discountPercentage = discountPercentage;
        this.couponCode = couponCode;
        this.createdAt = createdAt;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getClassName() {
        return className;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getPurchaseStatus() {
        return purchaseStatus;
    }

    public Integer getDiscountPercentage() {
        return discountPercentage;
    }

    public String getCouponCode() {
        return couponCode;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setPurchaseStatus(String purchaseStatus) {
        this.purchaseStatus = purchaseStatus;
    }

    public void setDiscountPercentage(Integer discountPercentage) {
        this.discountPercentage = discountPercentage;
    }

    public void setCouponCode(String couponCode) {
        this.couponCode = couponCode;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}