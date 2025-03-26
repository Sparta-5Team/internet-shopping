package com.example.team5project.domain.storefilter.dto.response;

import com.example.team5project.domain.storefilter.status.StoreStatus;
import lombok.Data;

import java.time.LocalDate;

@Data
public class StoreResponse {
    private Long id;
    private String name;
    private Integer rating;
    private StoreStatus status;
    private LocalDate monitoringDate;

    public StoreResponse(Long id, String name, Integer rating, StoreStatus status, LocalDate monitoringDate) {
        this.id = id;
        this.name = name;
        this.rating = rating;
        this.status = status;
        this.monitoringDate = monitoringDate;
    }
}
