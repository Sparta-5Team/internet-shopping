package com.example.team5project.domain.mall.dto.request;

import lombok.Getter;

import java.time.LocalDate;

@Getter
public class MallRequestDto {

    private String storeName;
    private Integer totalRating;
    private String storeStatus;
    private LocalDate monitoringDate;
}
