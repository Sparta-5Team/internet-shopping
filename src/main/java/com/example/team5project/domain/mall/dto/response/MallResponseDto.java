package com.example.team5project.domain.mall.dto.response;

import com.example.team5project.domain.mall.entity.Mall;
import lombok.Getter;

@Getter
public class MallResponseDto {

    private final Long id;
    private final String storeName;
    private final Integer totalRating;
    private final String storeStatus;
    private final String monitoringDate;

    public MallResponseDto(Mall mall) {
        this.id = mall.getId();
        this.storeName = mall.getStoreName();
        this.totalRating = mall.getTotalRating();
        this.storeStatus = mall.getStoreStatus();
        this.monitoringDate = mall.getMonitoringDate();
    }
}
