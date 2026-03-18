package com.example.discountoffer.dto;

public class FormResponseDto {

    private String status;
    private Integer discount;
    private String couponCode;
    private String redirectUrl;
    private String message;

    public FormResponseDto() {
    }

    public FormResponseDto(String status, Integer discount, String couponCode, String redirectUrl, String message) {
        this.status = status;
        this.discount = discount;
        this.couponCode = couponCode;
        this.redirectUrl = redirectUrl;
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public Integer getDiscount() {
        return discount;
    }

    public String getCouponCode() {
        return couponCode;
    }

    public String getRedirectUrl() {
        return redirectUrl;
    }

    public String getMessage() {
        return message;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setDiscount(Integer discount) {
        this.discount = discount;
    }

    public void setCouponCode(String couponCode) {
        this.couponCode = couponCode;
    }

    public void setRedirectUrl(String redirectUrl) {
        this.redirectUrl = redirectUrl;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}