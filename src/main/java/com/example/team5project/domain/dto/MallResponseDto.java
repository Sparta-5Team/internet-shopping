package com.example.team5project.domain.dto;

import com.example.team5project.domain.entity.Mall;
import lombok.Getter;

@Getter
public class MallResponseDto {

    private final Long id;
    private final String storeName;
    private final Long totalRating;
    private final String storeStatus;
    private final String monitoringDate;

    public MallResponseDto(Mall mall) {
        this.id = mall.getId();
        this.storeName = mall.getStoreName();
        this.totalRating = mall.getTotalRating();
        this.storeStatus = mall.getStoreStatus();
        this.monitoringDate = getMonitoringDate();
    }
}
