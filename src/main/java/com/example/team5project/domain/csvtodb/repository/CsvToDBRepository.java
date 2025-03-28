package com.example.team5project.domain.csvtodb.repository;

import com.example.team5project.domain.csvtodb.entity.CsvToDB;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CsvToDBRepository extends JpaRepository<CsvToDB, Long> {
}
