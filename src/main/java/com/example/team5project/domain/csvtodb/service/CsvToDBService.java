package com.example.team5project.domain.csvtodb.service;

import com.example.team5project.domain.csvtodb.DatabaseUtil;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CsvToDBService {

    private static final String CSV_FILE_PATH = "D:/workspace/internet-shopping/src/main/java/com/example/team5project/global/shopping.csv";
    private static final String ISERT_QUERY = "INSERT INTO csvtodb (name, rating, status, monitoringDate) VALUES (?, ?, ?, ?)";
    private static final int BATCH_SIZE = 100;

    public void insertMallData() {
        try (
                Connection connection = DatabaseUtil.getConnection();
                CSVReader reader = new CSVReader(new FileReader(CSV_FILE_PATH));
                PreparedStatement preparedStatement =connection.prepareStatement(ISERT_QUERY)
        ) {
            List<String[]> records = reader.readAll();

            String[] header = records.get(0);
            int nameIndex = findColumnIndex(header, "상호");
            int ratingIndex = findColumnIndex(header, "전체평가");
            int statusIndex = findColumnIndex(header, "업소상태");
            int dateIndex = findColumnIndex(header, "모니터링날짜");

            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            connection.setAutoCommit(false);

            int batchCount = 0;

            for (int i = 1; i < records.size(); i++) { // 헤더 제외
                String[] record = records.get(i);

                if (record.length < 4) {
                    System.err.println("잘못된 데이터 행: " + Arrays.toString(record));
                    continue;
                }

                preparedStatement.setString(1 , record[nameIndex]); // 상호 -> name
                preparedStatement.setInt(2, Integer.parseInt(record[ratingIndex])); // 전체평가 -> rating
                preparedStatement.setString(3, record[statusIndex]); // 업소상태 -> status

                java.util.Date parsedDate = dateFormat.parse(record[dateIndex]);
                preparedStatement.setDate(4, new Date(parsedDate.getTime())); // 모니터링날짜 -> monitoringDate

                preparedStatement.addBatch();
                batchCount++;

                if (batchCount % BATCH_SIZE == 0) {
                    preparedStatement.executeBatch();
                    connection.commit();
                    batchCount = 0;
                }
            }

            if (batchCount > 0) {
                preparedStatement.executeBatch();
                connection.commit();
            }


        } catch (IOException e) {
            System.err.println("CSV 파일 읽기 오류" + e.getMessage());
        } catch (SQLException e) {
            System.err.println("SQL 오류" + e.getMessage());
        } catch (ParseException e) {
            System.err.println("날짜 변환 오류" + e.getMessage());
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
//    private static final String CSV_FILE_PATH = "D:/workspace/internet-shopping/src/main/java/com/example/team5project/global/shopping.csv";
//    private static final String ISERT_QUERY = "INSERT INTO csvtodb (name, rating, status, monitoringDate) VALUES (?, ?, ?, ?)";
//
//
//    public void insertMallData() {
//        try (
//                Connection connection = DatabaseUtil.getConnection();
//                CSVReader reader = new CSVReader(new FileReader(CSV_FILE_PATH));
//                PreparedStatement preparedStatement =connection.prepareStatement(ISERT_QUERY)
//        ) {
//            List<String[]> records = reader.readAll();
//
//            String[] header = records.get(0);
//            int nameIndex = findColumnIndex(header, "상호");
//            int ratingIndex = findColumnIndex(header, "전체평가");
//            int statusIndex = findColumnIndex(header, "업소상태");
//            int dateIndex = findColumnIndex(header, "모니터링날짜");
//
//            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
//            connection.setAutoCommit(false);
//
//            for (int i = 1; i < records.size(); i++) { // 헤더 제외
//                String[] record = records.get(i);
//
//                if (record.length < 4) {
//                    System.err.println("잘못된 데이터 행: " + Arrays.toString(record));
//                    continue;
//                }
//
//                preparedStatement.setString(1 , record[nameIndex]); // 상호 -> name
//                preparedStatement.setInt(2, Integer.parseInt(record[ratingIndex])); // 전체평가 -> rating
//                preparedStatement.setString(3, record[statusIndex]); // 업소상태 -> status
//
//                java.util.Date parsedDate = dateFormat.parse(record[dateIndex]);
//                preparedStatement.setDate(4, new Date(parsedDate.getTime())); // 모니터링날짜 -> monitoringDate
//
//                preparedStatement.addBatch();
//            }
//
//            preparedStatement.executeBatch();
//            connection.commit();
//
//        } catch (IOException e) {
//            System.err.println("CSV 파일 읽기 오류" + e.getMessage());
//        } catch (SQLException e) {
//            System.err.println("SQL 오류" + e.getMessage());
//        } catch (ParseException e) {
//            System.err.println("날짜 변환 오류" + e.getMessage());
//        } catch (CsvException e) {
//            System.err.println("CSV parsing 오류");
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
