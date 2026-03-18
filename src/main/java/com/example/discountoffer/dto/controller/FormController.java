package com.example.discountoffer.dto.controller;

import com.example.discountoffer.dto.FormRequestDto;
import com.example.discountoffer.dto.FormResponseDto;
import com.example.discountoffer.service.LeadFormService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/forms")
@CrossOrigin("*")
public class FormController {

    @Autowired
    private LeadFormService leadFormService;

    @PostMapping("/submit")
    public FormResponseDto submit(@Valid @RequestBody FormRequestDto request) {
        return leadFormService.submitForm(request);
    }
}