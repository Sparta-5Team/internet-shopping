package com.example.team5project.domain.openapi.dto;

import lombok.Getter;

@Getter
public class OpenApiResponse {
    private final Long id;
    private final String company;
    private final String email;
    private final String shopname;
    private final String statin;

    public OpenApiResponse(Long id, String company, String email, String shopname, String statin) {
        this.id = id;
        this.company = company;
        this.email = email;
        this.shopname = shopname;
        this.statin = statin;
    }
}
