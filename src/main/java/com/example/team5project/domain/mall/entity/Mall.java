package com.example.team5project.domain.mall.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@Entity
@NoArgsConstructor
@Table(name = "mall_primary_data")
public class Mall {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "store_name")
    private String storeName;
    @Column(name = "total_rating")
    private Integer totalRating;
    @Column(name = "store_status")
    private String storeStatus;
    @Column(name = "monitoring_date")
    private LocalDate monitoringDate;

    public Mall(String storeName, Integer totalRating, String storeStatus, LocalDate monitoringDate) {
        this.storeName = storeName;
        this.totalRating = totalRating;
        this.storeStatus = storeStatus;
        this.monitoringDate = monitoringDate;
    }
}
