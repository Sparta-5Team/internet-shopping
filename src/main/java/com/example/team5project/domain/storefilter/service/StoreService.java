package com.example.team5project.domain.storefilter.service;

import com.example.team5project.domain.storefilter.dto.response.StorePageResponse;
import com.example.team5project.domain.storefilter.dto.request.StoreFilterRequest;
import com.example.team5project.domain.storefilter.dto.response.StoreResponse; // 패키지 수정
import com.example.team5project.domain.storefilter.entity.Store;
import com.example.team5project.domain.storefilter.repository.StoreQueryRepository;
import com.example.team5project.domain.storefilter.status.StoreStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StoreService {

    private final StoreQueryRepository storeQueryRepository;

    @Transactional(readOnly = true)
    public StorePageResponse getFilteredStores(StoreFilterRequest request) {
        List<Store> stores = storeQueryRepository.findStoresWithFilters(request);

        // 다음 페이지 여부 확인 및 결과 분리
        boolean hasNext = stores.size() > request.getSize();
        List<Store> result = hasNext ? stores.subList(0, request.getSize()) : stores;
        Long nextCursorId = hasNext ? result.get(result.size() - 1).getId() : null;

        // DTO 변환
        List<StoreResponse> content = result.stream()
                .map(store -> new StoreResponse(
                        store.getId(),
                        store.getName(),
                        store.getRating(),
                        store.getStatus(),
                        store.getMonitoringDate()
                ))
                .collect(Collectors.toList());

        return new StorePageResponse(content, nextCursorId, hasNext);
    }

    public List<StoreResponse> findStores(Integer rating, StoreStatus status) {
        List<Store> findAll = storeQueryRepository.findAll(rating, status);
        List<StoreResponse> dtos = new ArrayList<>();

        for (Store store : findAll) {
            dtos.add(new StoreResponse(store.getId(), store.getName(), store.getRating(), store.getStatus(), store.getMonitoringDate()));
        }

        return dtos;
    }
}