package com.example.discountoffer.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "lead_forms")
public class LeadForm {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(name = "class_name", nullable = false)
    private String className;

    @Column(name = "phone_number", nullable = false)
    private String phoneNumber;

    @Column(name = "purchase_status", nullable = false)
    private String purchaseStatus;

    @Column(name = "discount_percentage")
    private Integer discountPercentage;

    @Column(name = "coupon_code")
    private String couponCode;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

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