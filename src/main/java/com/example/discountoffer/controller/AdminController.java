package com.example.discountoffer.controller;

import com.example.discountoffer.dto.AdminLoginRequestDto;
import com.example.discountoffer.dto.AdminLoginResponseDto;
import com.example.discountoffer.dto.CouponOfferDto;
import com.example.discountoffer.dto.LeadFormResponseDto;
import com.example.discountoffer.service.LeadFormService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/admin")
@CrossOrigin("*")
public class AdminController {

    @Autowired
    private LeadFormService leadFormService;

    private static final String ADMIN_USERNAME = "admin";
    private static final String ADMIN_PASSWORD = "123456";

    @PostMapping("/login")
    public AdminLoginResponseDto login(@RequestBody AdminLoginRequestDto request) {
        if (ADMIN_USERNAME.equals(request.getUsername()) &&
                ADMIN_PASSWORD.equals(request.getPassword())) {
            return new AdminLoginResponseDto(true, "Login successful");
        }
        return new AdminLoginResponseDto(false, "Invalid username or password");
    }

    @GetMapping("/forms")
    public List<LeadFormResponseDto> getForms(
            @RequestParam(required = false) String fromDate,
            @RequestParam(required = false) String toDate,
            @RequestParam(required = false) String purchaseStatus
    ) {
        return leadFormService.filterForms(fromDate, toDate, purchaseStatus);
    }

    @GetMapping("/forms/export")
    public void exportCsv(
            @RequestParam(required = false) String fromDate,
            @RequestParam(required = false) String toDate,
            @RequestParam(required = false) String purchaseStatus,
            HttpServletResponse response
    ) throws IOException {

        response.setContentType("text/csv");
        response.setHeader("Content-Disposition", "attachment; filename=form_responses.csv");

        leadFormService.writeFormsAsCsv(response.getWriter(), fromDate, toDate, purchaseStatus);
    }

    @PostMapping("/coupon-offers")
    public CouponOfferDto saveCouponOffer(@RequestBody CouponOfferDto dto) {
        return leadFormService.saveCouponOffer(dto);
    }

    @GetMapping("/coupon-offers")
    public List<CouponOfferDto> getAllCouponOffers() {
        return leadFormService.getAllCouponOffers();
    }

    @PutMapping("/coupon-offers/{id}")
    public CouponOfferDto updateCouponOffer(@PathVariable Long id, @RequestBody CouponOfferDto dto) {
        return leadFormService.updateCouponOffer(id, dto);
    }

    @DeleteMapping("/coupon-offers/{id}")
    public String deleteCouponOffer(@PathVariable Long id) {
        leadFormService.deleteCouponOffer(id);
        return "Coupon offer deleted successfully";
    }
}