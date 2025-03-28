package com.example.team5project.domain.openapi.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QOpenApi is a Querydsl query type for OpenApi
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QOpenApi extends EntityPathBase<OpenApi> {

    private static final long serialVersionUID = 2023319761L;

    public static final QOpenApi openApi = new QOpenApi("openApi");

    public final StringPath company = createString("company");

    public final StringPath email = createString("email");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath shopname = createString("shopname");

    public final StringPath statin = createString("statin");

    public QOpenApi(String variable) {
        super(OpenApi.class, forVariable(variable));
    }

    public QOpenApi(Path<? extends OpenApi> path) {
        super(path.getType(), path.getMetadata());
    }

    public QOpenApi(PathMetadata metadata) {
        super(OpenApi.class, metadata);
    }

}

