package com.example.team5project.domain.csvtodb.controller;

import com.example.team5project.domain.csvtodb.dto.response.CsvToDBResponseDto;
import com.example.team5project.domain.csvtodb.service.CsvToDBService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class CsvToDBController {

    private final CsvToDBService csvToDBService;

    @PostMapping("/collection")
    public ResponseEntity<CsvToDBResponseDto>  insertCSVToDB() {
        try {
            csvToDBService.insertMallData();
            CsvToDBResponseDto csvToDBResponseDto = new CsvToDBResponseDto(true, "CSV -> DB 성공");
            return ResponseEntity.ok(csvToDBResponseDto);
        } catch (Exception e) {
            CsvToDBResponseDto csvToDBResponseDto = new CsvToDBResponseDto(false, "CSV -> DB 실패" + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(csvToDBResponseDto);
        }

    }
}
