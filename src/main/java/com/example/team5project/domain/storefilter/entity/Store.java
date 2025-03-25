package com.example.team5project.domain.storefilter.entity;

import com.example.team5project.domain.storefilter.status.StoreStatus;
import jakarta.persistence.*;
import lombok.Getter;

import java.time.LocalDate;

@Entity
@Getter
@Table(name = "store")
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
}
