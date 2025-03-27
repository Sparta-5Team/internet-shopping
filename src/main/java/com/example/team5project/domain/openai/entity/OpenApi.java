package com.example.team5project.domain.openai.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "openapi")
@Getter
@Builder
@AllArgsConstructor
public class OpenApi {

    @Id @GeneratedValue
    private Long id;

    @Column
    private String company;

    @Column
    private String shopname;

    @Column
    private String email;

    @Column
    private String statin;


    public OpenApi() {
    }
}
