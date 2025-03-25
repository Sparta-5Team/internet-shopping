package com.example.team5project.domain.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor
public class Mall {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String storeName;
    private Long totalRating;
    private String storeStatus;
    private String monitoringDate;

    public Mall(String storeName, Long totalRating, String storeStatus, String monitoringDate) {
        this.storeName = storeName;
        this.totalRating = totalRating;
        this.storeStatus = storeStatus;
        this.monitoringDate = monitoringDate;
    }
}
