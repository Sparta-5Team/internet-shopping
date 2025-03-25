package com.example.team5project.domain.storefilter.specification;

import com.example.team5project.domain.storefilter.entity.Store;
import com.example.team5project.domain.storefilter.status.StoreStatus;
import org.springframework.data.jpa.domain.Specification;

public class StoreSpecification {

    public static Specification<Store> filterByRating(Double rating) {
        return ((root, query, criteriaBuilder) ->
                rating != null ? criteriaBuilder.equal(root.get("rating"), rating) : null);
    }

    public static Specification<Store> filterByStatus(StoreStatus status) {
        return ((root, query, criteriaBuilder) ->
                status != null ? criteriaBuilder.equal(root.get("status"), status) : null);
    }

    public static Specification<Store> orderByMonitoringDateDesc() {
        return (root, query, criteriaBuilder) -> {
          query.orderBy(criteriaBuilder.desc(root.get("monitoringDate")));
          return null;
        };
    }
}

