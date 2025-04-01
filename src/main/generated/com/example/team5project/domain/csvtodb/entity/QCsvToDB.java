package com.example.team5project.domain.csvtodb.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QCsvToDB is a Querydsl query type for CsvToDB
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QCsvToDB extends EntityPathBase<CsvToDB> {

    private static final long serialVersionUID = -602137295L;

    public static final QCsvToDB csvToDB = new QCsvToDB("csvToDB");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final DatePath<java.time.LocalDate> monitoringDate = createDate("monitoringDate", java.time.LocalDate.class);

    public final StringPath name = createString("name");

    public final NumberPath<Integer> rating = createNumber("rating", Integer.class);

    public final StringPath status = createString("status");

    public QCsvToDB(String variable) {
        super(CsvToDB.class, forVariable(variable));
    }

    public QCsvToDB(Path<? extends CsvToDB> path) {
        super(path.getType(), path.getMetadata());
    }

    public QCsvToDB(PathMetadata metadata) {
        super(CsvToDB.class, metadata);
    }

}

