package com.example.team5project.domain.storefilter.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QStore is a Querydsl query type for Store
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QStore extends EntityPathBase<Store> {

    private static final long serialVersionUID = 223172121L;

    public static final QStore store = new QStore("store");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final DatePath<java.time.LocalDate> monitoringDate = createDate("monitoringDate", java.time.LocalDate.class);

    public final StringPath name = createString("name");

    public final NumberPath<Integer> rating = createNumber("rating", Integer.class);

    public final EnumPath<com.example.team5project.domain.storefilter.status.StoreStatus> status = createEnum("status", com.example.team5project.domain.storefilter.status.StoreStatus.class);

    public QStore(String variable) {
        super(Store.class, forVariable(variable));
    }

    public QStore(Path<? extends Store> path) {
        super(path.getType(), path.getMetadata());
    }

    public QStore(PathMetadata metadata) {
        super(Store.class, metadata);
    }

}

