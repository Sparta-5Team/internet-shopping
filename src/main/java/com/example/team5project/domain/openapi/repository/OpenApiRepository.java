package com.example.team5project.domain.openapi.repository;

import com.example.team5project.domain.openapi.entity.OpenApi;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OpenApiRepository extends JpaRepository<OpenApi, Long> {
}
