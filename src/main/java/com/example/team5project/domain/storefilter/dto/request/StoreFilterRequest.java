package com.example.team5project.domain.storefilter.dto.request;

import com.example.team5project.domain.storefilter.status.StoreStatus;
import lombok.Data;

@Data
public class StoreFilterRequest {
    private Integer rating;     // 전체평가 필터 (nullable)
    private StoreStatus status; // 업소상태 필터 (nullable)
    private Long cursorId;      // 커서 ID (null이면 첫 페이지)
    private int size = 10;      // 페이지 크기 (기본값 10)
}
