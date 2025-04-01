package com.example.team5project.domain.csvtodb.service;

import com.example.team5project.domain.csvtodb.entity.CsvToDB;
import com.example.team5project.domain.csvtodb.repository.CsvToDBRepository;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CsvToDBService {

    private final CsvToDBRepository csvToDBRepository;
    private static final String CSV_FILE_PATH = "D:/workspace/internet-shopping/src/main/java/com/example/team5project/global/shopping.csv";
    private static final int BATCH_SIZE = 100;

    public void insertMallData() {
        try (CSVReader reader = new CSVReader(new FileReader(CSV_FILE_PATH));) {
            List<String[]> records = reader.readAll();

            String[] header = records.get(0);
            int nameIndex = findColumnIndex(header, "상호");
            int ratingIndex = findColumnIndex(header, "전체평가");
            int statusIndex = findColumnIndex(header, "업소상태");
            int dateIndex = findColumnIndex(header, "모니터링날짜");

            DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");

            List<CsvToDB> batchList = new ArrayList<>();
            int batchCount = 0;

            for (int i = 1; i < records.size(); i++) { // 헤더 제외
                String[] record = records.get(i);

                if (record.length < 4) {
                    System.err.println("잘못된 데이터 행: " + Arrays.toString(record));
                    continue;
                }

                String name = record[nameIndex]; // 상호 -> name
                Integer rating = Integer.parseInt(record[ratingIndex]); // 전체평가 -> rating
                String status = record[statusIndex]; // 업소상태 -> status
                LocalDate monitoringDate = LocalDate.parse(record[dateIndex], dateFormat); // 모니터링날짜 -> monitoringDate

                CsvToDB csvToDB = new CsvToDB(name, rating, status, monitoringDate);
                batchList.add(csvToDB);
                batchCount++;

                if (batchCount % BATCH_SIZE == 0) {
                    csvToDBRepository.saveAll(batchList);
                    batchList.clear();
                }
            }

            if (!batchList.isEmpty()) {
                csvToDBRepository.saveAll(batchList);
            }


        } catch (IOException e) {
            System.err.println("CSV 파일 읽기 오류" + e.getMessage());
        } catch (NumberFormatException e) {
            System.err.println("데이터 형식 오류" + e.getMessage());
        } catch (CsvException e) {
            System.err.println("CSV parsing 오류" + e.getMessage());
        }
    }

    private int findColumnIndex(String[] header, String columnName) {
        for (int i = 0; i < header.length; i++) {
            if (header[i].trim().equals(columnName)) {
                return i;
            }
        }
        return -1; // 찾지 못한 경우
    }
}


// 테스트용 코드 개선 전 코드
//
//    private final CsvToDBRepository csvToDBRepository;
//    private static final String CSV_FILE_PATH = "D:/workspace/internet-shopping/src/main/java/com/example/team5project/global/shopping.csv";
//
//
//    public void insertMallData() {
//        try (CSVReader reader = new CSVReader(new FileReader(CSV_FILE_PATH));) {
//
//            List<String[]> records = reader.readAll();
//
//            String[] header = records.get(0);
//            int nameIndex = findColumnIndex(header, "상호");
//            int ratingIndex = findColumnIndex(header, "전체평가");
//            int statusIndex = findColumnIndex(header, "업소상태");
//            int dateIndex = findColumnIndex(header, "모니터링날짜");
//
//            DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");
//
//            List<CsvToDB> batchList = new ArrayList<>();
//
//            for (int i = 1; i < records.size(); i++) { // 헤더 제외
//                String[] record = records.get(i);
//
//                if (record.length < 4) {
//                    System.err.println("잘못된 데이터 행: " + Arrays.toString(record));
//                    continue;
//                }
//
//                String name = record[nameIndex]; // 상호 -> name
//                Integer rating = Integer.parseInt(record[ratingIndex]); // 전체평가 -> rating
//                String status = record[statusIndex]; // 업소상태 -> status
//                LocalDate monitoringDate = LocalDate.parse(record[dateIndex], dateFormat); // 모니터링날짜 -> monitoringDate
//
//                CsvToDB csvToDB = new CsvToDB(name, rating, status, monitoringDate);
//                batchList.add(csvToDB);
//            }
//            csvToDBRepository.saveAll(batchList);
//
//
//
//        } catch (IOException e) {
//            System.err.println("CSV 파일 읽기 오류" + e.getMessage());
//        } catch (NumberFormatException e) {
//            System.err.println("데이터 형식 오류" + e.getMessage());
//        } catch (CsvException e) {
//            System.err.println("CSV parsing 오류" + e.getMessage());
//        }
//    }
//
//    private int findColumnIndex(String[] header, String columnName) {
//        for (int i = 0; i < header.length; i++) {
//            if (header[i].trim().equals(columnName)) {
//                return i;
//            }
//        }
//        return -1; // 찾지 못한 경우
//    }
//}
