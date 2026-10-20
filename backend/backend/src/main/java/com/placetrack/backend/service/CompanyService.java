package com.placetrack.backend.service;

import com.placetrack.backend.dto.CompanyRequest;
import com.placetrack.backend.dto.CompanyResponse;
import com.placetrack.backend.entity.Company;
import com.placetrack.backend.exception.DuplicateCompanyException;
import com.placetrack.backend.exception.ResourceNotFoundException;
import com.placetrack.backend.repository.CompanyRepository;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompanyService {

    private final CompanyRepository companyRepository;

    public CompanyService(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    private CompanyResponse convertToResponse(Company company) {
        return new CompanyResponse(
                company.getId(),
                company.getName(),
                company.getIndustry(),
                company.getLocation(),
                company.getWebsite(),
                company.getDescription()
        );
    }

    public CompanyResponse addCompany(CompanyRequest request) {

        if (companyRepository.existsByNameIgnoreCase(request.getName().trim())) {
            throw new DuplicateCompanyException("Company already exists");
        }

        Company company = new Company(
                request.getName().trim(),
                request.getIndustry(),
                request.getLocation(),
                request.getWebsite(),
                request.getDescription()
        );

        return convertToResponse(companyRepository.save(company));
    }

    public List<CompanyResponse> getAllCompanies() {
        return companyRepository.findAll()
                .stream()
                .map(this::convertToResponse)
                .toList();
    }

    public CompanyResponse getCompanyById(Long id) {
        Company company = companyRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Company not found: " + id));

        return convertToResponse(company);
    }

    public CompanyResponse updateCompany(Long id, CompanyRequest request) {

        Company company = companyRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Company not found: " + id));

        if (companyRepository.existsByNameIgnoreCaseAndIdNot(
                request.getName().trim(), id)) {
            throw new DuplicateCompanyException("Company name already exists");
        }

        company.setName(request.getName().trim());
        company.setIndustry(request.getIndustry());
        company.setLocation(request.getLocation());
        company.setWebsite(request.getWebsite());
        company.setDescription(request.getDescription());

        return convertToResponse(companyRepository.save(company));
    }

    public void deleteCompany(Long id) {

        Company company = companyRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Company not found: " + id));

        companyRepository.delete(company);
    }
}
