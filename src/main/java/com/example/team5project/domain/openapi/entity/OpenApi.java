package com.example.team5project.domain.openapi.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Entity
@Table(name = "openapi", indexes = {
        @Index(name = "idx_comapny", columnList = "company")
})
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
