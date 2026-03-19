package com.example.discountoffer.service;

import com.example.discountoffer.dto.CouponOfferDto;
import com.example.discountoffer.dto.FormRequestDto;
import com.example.discountoffer.dto.FormResponseDto;
import com.example.discountoffer.dto.LeadFormResponseDto;
import com.example.discountoffer.entity.CouponOffer;
import com.example.discountoffer.entity.LeadForm;
import com.example.discountoffer.repository.CouponOfferRepository;
import com.example.discountoffer.repository.LeadFormRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;

@Service
public class LeadFormService {

    @Autowired
    private LeadFormRepository leadFormRepository;

    @Autowired
    private CouponOfferRepository couponOfferRepository;

    private final Random random = new Random();

    public FormResponseDto submitForm(FormRequestDto request) {

        Optional<LeadForm> existingLead = leadFormRepository.findByPhoneNumber(request.getPhoneNumber());
        if (existingLead.isPresent()) {
            return new FormResponseDto(
                    "ALREADY_SUBMITTED",
                    null,
                    null,
                    null,
                    "This phone number has already submitted the form."
            );
        }

        LeadForm leadForm = new LeadForm();
        leadForm.setName(request.getName());
        leadForm.setClassName(request.getClassName());
        leadForm.setPhoneNumber(request.getPhoneNumber());
        leadForm.setPurchaseStatus(request.getPurchaseStatus());
        leadForm.setCreatedAt(LocalDateTime.now());

        if ("PURCHASED".equalsIgnoreCase(request.getPurchaseStatus())) {
            CouponOffer selectedCoupon = getRandomCouponOffer();

            if (selectedCoupon == null) {
                return new FormResponseDto(
                        "PURCHASED",
                        0,
                        "FRIEND50",
                        "/thank-you",
                        "If you already purchase, you can share this code to your friend"
                );
            }

            leadForm.setDiscountPercentage(selectedCoupon.getDiscountPercentage());
            leadForm.setCouponCode(selectedCoupon.getCouponCode());
            leadFormRepository.save(leadForm);

            return new FormResponseDto(
                    "PURCHASED",
                    selectedCoupon.getDiscountPercentage(),
                    selectedCoupon.getCouponCode(),
                    "/thank-you",
                    "If you already purchase, you can share this code to your friend"
            );
        }

        CouponOffer selectedCoupon = getRandomCouponOffer();


        if (selectedCoupon == null) {
            return new FormResponseDto(
                    "NO_COUPON_AVAILABLE",
                    null,
                    null,
                    null,
                    "No offers available right now. Please try again later."
            );
        }

        Integer discount = selectedCoupon.getDiscountPercentage();
        String couponCode = selectedCoupon.getCouponCode();

        leadForm.setDiscountPercentage(discount);
        leadForm.setCouponCode(couponCode);
        leadFormRepository.save(leadForm);

        return new FormResponseDto(
                "NOT_PURCHASED",
                discount,
                couponCode,
                "/offer-page",
                "Congratulations! Your discount code has been generated."
        );
    }

    public List<LeadFormResponseDto> getAllForms() {
        return leadFormRepository.findAll()
                .stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    public List<LeadFormResponseDto> filterForms(String fromDate, String toDate, String purchaseStatus) {
        boolean hasDateFilter = fromDate != null && !fromDate.isBlank()
                && toDate != null && !toDate.isBlank();
        boolean hasStatusFilter = purchaseStatus != null && !purchaseStatus.isBlank();

        List<LeadForm> forms;

        if (hasDateFilter && hasStatusFilter) {
            LocalDateTime start = LocalDate.parse(fromDate).atStartOfDay();
            LocalDateTime end = LocalDate.parse(toDate).atTime(LocalTime.MAX);
            forms = leadFormRepository.findByCreatedAtBetweenAndPurchaseStatusIgnoreCase(
                    start, end, purchaseStatus
            );
        } else if (hasDateFilter) {
            LocalDateTime start = LocalDate.parse(fromDate).atStartOfDay();
            LocalDateTime end = LocalDate.parse(toDate).atTime(LocalTime.MAX);
            forms = leadFormRepository.findByCreatedAtBetween(start, end);
        } else if (hasStatusFilter) {
            forms = leadFormRepository.findByPurchaseStatusIgnoreCase(purchaseStatus);
        } else {
            forms = leadFormRepository.findAll();
        }

        return forms.stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    public void writeFormsAsCsv(PrintWriter writer, String fromDate, String toDate, String purchaseStatus) {
        List<LeadFormResponseDto> forms = filterForms(fromDate, toDate, purchaseStatus);

        writer.println("ID,Name,Class,Phone Number,Purchase Status,Discount Percentage,Coupon Code,Created At");

        for (LeadFormResponseDto form : forms) {
            writer.printf("%d,%s,%s,%s,%s,%s,%s,%s%n",
                    form.getId(),
                    safeCsv(form.getName()),
                    safeCsv(form.getClassName()),
                    safeCsv(form.getPhoneNumber()),
                    safeCsv(form.getPurchaseStatus()),
                    form.getDiscountPercentage() != null ? form.getDiscountPercentage() : "",
                    safeCsv(form.getCouponCode()),
                    form.getCreatedAt() != null ? form.getCreatedAt().toString() : ""
            );
        }

        writer.flush();
    }

    public CouponOfferDto saveCouponOffer(CouponOfferDto dto) {
        CouponOffer couponOffer = new CouponOffer();
        couponOffer.setDiscountPercentage(dto.getDiscountPercentage());
        couponOffer.setCouponCode(dto.getCouponCode());

        CouponOffer saved = couponOfferRepository.save(couponOffer);

        CouponOfferDto response = new CouponOfferDto();
        response.setId(saved.getId());
        response.setDiscountPercentage(saved.getDiscountPercentage());
        response.setCouponCode(saved.getCouponCode());

        return response;
    }

    public List<CouponOfferDto> getAllCouponOffers() {
        return couponOfferRepository.findAll()
                .stream()
                .map(coupon -> {
                    CouponOfferDto dto = new CouponOfferDto();
                    dto.setId(coupon.getId());
                    dto.setDiscountPercentage(coupon.getDiscountPercentage());
                    dto.setCouponCode(coupon.getCouponCode());
                    return dto;
                })
                .collect(Collectors.toList());
    }

    public CouponOfferDto updateCouponOffer(Long id, CouponOfferDto dto) {
        CouponOffer couponOffer = couponOfferRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Coupon offer not found"));

        couponOffer.setDiscountPercentage(dto.getDiscountPercentage());
        couponOffer.setCouponCode(dto.getCouponCode());

        CouponOffer updated = couponOfferRepository.save(couponOffer);

        CouponOfferDto response = new CouponOfferDto();
        response.setId(updated.getId());
        response.setDiscountPercentage(updated.getDiscountPercentage());
        response.setCouponCode(updated.getCouponCode());

        return response;
    }

    public void deleteCouponOffer(Long id) {
        CouponOffer couponOffer = couponOfferRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Coupon offer not found"));

        couponOfferRepository.delete(couponOffer);
    }

    private CouponOffer getRandomCouponOffer() {
        List<CouponOffer> offers = couponOfferRepository.findAll();

        if (offers.isEmpty()) {
            return null;
        }

        int index = random.nextInt(offers.size());
        return offers.get(index);
    }

    private LeadFormResponseDto mapToDto(LeadForm form) {
        return new LeadFormResponseDto(
                form.getId(),
                form.getName(),
                form.getClassName(),
                form.getPhoneNumber(),
                form.getPurchaseStatus(),
                form.getDiscountPercentage(),
                form.getCouponCode(),
                form.getCreatedAt()
        );
    }

    private String safeCsv(String value) {
        if (value == null) {
            return "";
        }
        return "\"" + value.replace("\"", "\"\"") + "\"";
    }

    private int generateDiscount() {
        return random.nextInt(20) + 51;
    }

    private String generateCouponCode(int discount) {
        String[] prefixes = {"SAVE", "OFFER", "DEAL"};
        String prefix = prefixes[random.nextInt(prefixes.length)];
        return prefix + discount;
    }
}