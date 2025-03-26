package com.example.team5project.domain.mall.service;

import com.example.team5project.domain.mall.entity.Mall;
import com.example.team5project.domain.mall.repository.MallRepository;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class CsvToDBService {

    private final MallRepository mallRepository;
    private static final String CSV_FILE_PATH = "src/main/java/com/example/team5project/global/shopping.csv";
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    @Transactional
    public void insertMallDataFromCsv() {
        try (CSVReader csvReader = new CSVReader(new FileReader(CSV_FILE_PATH))) {
            String[] headers = csvReader.readNext();
            if (headers == null) {
                throw new RuntimeException("CSV파일이 비어있습니다.");
            }

            Map<String, Integer> columnIndexMap = mapColumnIndexes(headers);

            String[] values;
            while ((values = csvReader.readNext()) != null) {
                Mall mall = parseCsvToMall(values, columnIndexMap);
                mallRepository.save(mall);
            }
        } catch (IOException | CsvValidationException e) {
            throw new RuntimeException("CSV 파일을 읽는 중 오류 발생: " + e.getMessage());
        }
    }

    private Map<String, Integer> mapColumnIndexes(String[] headers) {
        Map<String, Integer> columnIndexMap = new HashMap<>();

        for (int i = 0; i < headers.length; i++) {
            switch (headers[i].trim()) {
                case "상호":
                    columnIndexMap.put("storeName", i);
                    break;
                case "전체평가":
                    columnIndexMap.put("totalRating", i);
                    break;
                case "업소상태":
                    columnIndexMap.put("storeStatus", i);
                    break;
                case "모니터링날짜":
                    columnIndexMap.put("monitoringDate", i);
                    break;
            }
        }

        // 필수 컬럼 검증
        if (!columnIndexMap.containsKey("storeName") ||
                !columnIndexMap.containsKey("totalRating") ||
                !columnIndexMap.containsKey("storeStatus") ||
                !columnIndexMap.containsKey("monitoringDate")) {
            throw new IllegalArgumentException("CSV 파일에 필요한 컬럼이 없습니다.");
        }

        return columnIndexMap;
    }

    private Mall parseCsvToMall(String[] values, Map<String, Integer> columnIndexMap) {
        try {
            String storeName = values[columnIndexMap.get("storeName")].trim();
            Integer totalRating = Integer.parseInt(values[columnIndexMap.get("totalRating")].trim());
            String storeStatus = values[columnIndexMap.get("storeStatus")].trim();
            LocalDate monitoringDate = LocalDate.parse(values[columnIndexMap.get("monitoringDate")].trim(), DATE_FORMATTER);

            return new Mall(storeName, totalRating, storeStatus, monitoringDate);
        } catch (Exception e) {
            throw new RuntimeException("CSV 데이터 파싱 오류: " + e.getMessage());
        }
    }
}
