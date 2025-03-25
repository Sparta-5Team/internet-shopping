package com.example.team5project.domain.storefilter.entity;

import com.example.team5project.domain.storefilter.status.StoreStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Getter
@Table(name = "store")
@NoArgsConstructor
public class Store {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private Integer rating; //전체평가 점수


    @Enumerated(EnumType.STRING)
    private StoreStatus status; //업소상태

    @Column(name = "monitoring_date")
    private LocalDate monitoringDate; // 모니터링 날짜

    public Store(String name, Integer rating, StoreStatus status, LocalDate monitoringDate) {
        this.name = name;
        this.rating = rating;
        this.status = status;
        this.monitoringDate = monitoringDate;
    }
}
