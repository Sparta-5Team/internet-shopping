package com.example.team5project.domain.csvtodb.controller;

import com.example.team5project.domain.csvtodb.service.CsvToDBService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class CsvToDBController {

    private final CsvToDBService csvToDBService;

    @PostMapping("/collection")
    public ResponseEntity<String>  insertCSVToDB() {
        csvToDBService.insertMallData();
        return ResponseEntity.ok("CSV -> DB 성공");
    }
}
