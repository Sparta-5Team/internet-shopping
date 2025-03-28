package com.example.team5project.domain.csvtodb.dto.response;

import lombok.Getter;

@Getter
public class CsvToDBResponseDto {

    private final boolean success;
    private final String message;

    public CsvToDBResponseDto(boolean success, String message) {
        this.success = success;
        this.message = message;
    }
}
