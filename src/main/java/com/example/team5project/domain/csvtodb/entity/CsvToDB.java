package com.example.team5project.domain.csvtodb.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "csvtodb"/*, indexes = {
        @Index(name = "idx_name", columnList = "name"),
        @Index(name = "idx_rating", columnList = "rating"),
        @Index(name = "idx_status", columnList = "status"),
        @Index(name = "idx_monitoring_date", columnList = "monitoring_date")
}*/)
public class CsvToDB {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "rating", nullable = false)
    private Integer rating;

    @Column(name = "status", nullable = false)
    private String status;

    @Column(name = "monitoring_date", nullable = false)
    private LocalDate monitoringDate;

    public CsvToDB(String name, Integer rating, String status, LocalDate monitoringDate) {
        this.name = name;
        this.rating = rating;
        this.status = status;
        this.monitoringDate = monitoringDate;
    }
}
