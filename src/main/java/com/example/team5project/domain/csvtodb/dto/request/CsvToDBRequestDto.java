package com.example.team5project.domain.csvtodb.dto.request;

import lombok.Getter;

import java.time.LocalDate;

@Getter
public class CsvToDBRequestDto {

    private String name;
    private Integer rating;
    private String status;
    private LocalDate monitoringDate;
}
