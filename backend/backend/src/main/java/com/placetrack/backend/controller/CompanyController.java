package com.placetrack.backend.controller;

import com.placetrack.backend.dto.CompanyRequest;
import com.placetrack.backend.dto.CompanyResponse;
import com.placetrack.backend.service.CompanyService;

import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/companies")
@CrossOrigin(origins = "*")
public class CompanyController {

    private final CompanyService companyService;

    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    // Add Company
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CompanyResponse addCompany(
            @Valid @RequestBody CompanyRequest request) {

        return companyService.addCompany(request);
    }

    // Get All Companies
    @GetMapping
    public List<CompanyResponse> getAllCompanies() {

        return companyService.getAllCompanies();
    }

    // Get Company By ID
    @GetMapping("/{id}")
    public CompanyResponse getCompanyById(@PathVariable Long id) {

        return companyService.getCompanyById(id);
    }

    // Update Company
    @PutMapping("/{id}")
    public CompanyResponse updateCompany(
            @PathVariable Long id,
            @Valid @RequestBody CompanyRequest request) {

        return companyService.updateCompany(id, request);
    }

    // Delete Company
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCompany(@PathVariable Long id) {

        companyService.deleteCompany(id);
    }
}