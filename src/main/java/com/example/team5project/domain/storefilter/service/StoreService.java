package com.example.team5project.domain.storefilter.service;

import com.example.team5project.domain.storefilter.entity.Store;
import com.example.team5project.domain.storefilter.repository.StoreRepository;
import com.example.team5project.domain.storefilter.specification.StoreSpecification;
import com.example.team5project.domain.storefilter.status.StoreStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StoreService {

    private final StoreRepository storeRepository;

    @Transactional
    public List<Store> getFilteredStores(Double rating, StoreStatus status) {
        Specification<Store> spec = Specification.where(StoreSpecification.orderByMonitoringDateDesc());

        if (rating != null) {
            spec = spec.and(StoreSpecification.filterByRating(rating));
        }
        if (status != null) {
            spec = spec.and(StoreSpecification.filterByStatus(status));
        }

        return storeRepository.findAll(spec, PageRequest.of(0, 10)).getContent();
    }
}
