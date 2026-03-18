package com.example.discountoffer.dto;

import jakarta.validation.constraints.NotBlank;

public class FormRequestDto {

    @NotBlank(message = "Name is required")
    private String name;

    @NotBlank(message = "Class is required")
    private String className;

    @NotBlank(message = "Phone number is required")
    private String phoneNumber;

    @NotBlank(message = "Purchase status is required")
    private String purchaseStatus;

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
}