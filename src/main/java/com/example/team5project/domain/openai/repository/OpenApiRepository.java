package com.example.team5project.domain.openai.repository;

import com.example.team5project.domain.openai.entity.OpenApi;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OpenApiRepository extends JpaRepository<OpenApi, Long> {
}
