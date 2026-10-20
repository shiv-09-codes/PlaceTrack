package com.placetrack.backend.dto;

public class CompanyResponse {

    private Long id;
    private String name;
    private String industry;
    private String location;
    private String website;
    private String description;

    public CompanyResponse(Long id, String name, String industry,
                           String location, String website, String description) {
        this.id = id;
        this.name = name;
        this.industry = industry;
        this.location = location;
        this.website = website;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getIndustry() {
        return industry;
    }

    public String getLocation() {
        return location;
    }

    public String getWebsite() {
        return website;
    }

    public String getDescription() {
        return description;
    }
}