package com.example.team5project.domain.mall.controller;

import com.example.team5project.domain.mall.service.CsvToDBService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class CsvToDBController {

    private final CsvToDBService csvToDBService;

    @PostMapping("/collection")
    public ResponseEntity<String> insertMallData() {
        csvToDBService.insertMallDataFromCsv();
        return ResponseEntity.ok("CSV -> DB 저장 성공.");
    }
}
