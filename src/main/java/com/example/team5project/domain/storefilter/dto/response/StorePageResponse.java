package com.example.team5project.domain.storefilter.dto.response;

import java.util.List;

public record StorePageResponse(List<StoreResponse> content, Long nextCursorId, boolean hasNext) {
}
