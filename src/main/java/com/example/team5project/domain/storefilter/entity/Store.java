package com.example.team5project.domain.storefilter.entity;

import com.example.team5project.domain.storefilter.status.StoreStatus;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class Store {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private Double rating; //전체평가 점수


    @Enumerated(EnumType.STRING)
    private StoreStatus status; //업소상태

    private LocalDate monitoringDate; // 모니터링 날짜
}
