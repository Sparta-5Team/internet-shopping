package com.example.team5project.domain.storefilter.repository;

import com.example.team5project.domain.storefilter.dto.request.StoreFilterRequest;
import com.example.team5project.domain.storefilter.entity.QStore;
import com.example.team5project.domain.storefilter.entity.Store;
import com.example.team5project.domain.storefilter.status.StoreStatus;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class StoreQueryRepository {

    private final JPAQueryFactory queryFactory;
    private static final QStore store = QStore.store;

    public StoreQueryRepository(JPAQueryFactory queryFactory) {
        this.queryFactory = queryFactory;
    }

    public List<Store> findStoresWithFilters(StoreFilterRequest request) {
        return queryFactory
                .selectFrom(store)
                .where(
                        eqRating(request.getRating()),
                        eqStatus(request.getStatus()),
                        cursorCondition(request.getCursorId())
                )
                .orderBy(store.monitoringDate.desc(), store.id.desc())
                .limit(request.getSize() + 1)
                .fetch();
    }

    private BooleanExpression eqRating(Integer rating) {
        return rating != null ? store.rating.eq(rating) : null;
    }

    private BooleanExpression eqStatus(StoreStatus status) {
        return status != null ? store.status.eq(status) : null;
    }

    private BooleanExpression cursorCondition(Long cursorId) {
        return cursorId != null ? store.id.lt(cursorId) : null;
    }
}