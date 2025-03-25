package com.example.team5project.domain.repository;

import com.example.team5project.domain.entity.Mall;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MallRepository extends JpaRepository<Mall, Long> {

    List<Mall> findByTotalRating(Long totalRating);

    List<Mall> findByStoreStatus(String storeStatus);

    @Query("SELECT m FROM Mall m WHERE (:totalRating IS NULL OR m.totalRating = :totalRating)" + "AND (:storeStatus IS NULL OR m.storeStatus = :storeStatus)")
    List<Mall> findByTotalRatingAndStoreStatus(@Param("totalRating") Long totalRating, @Param("storeStatus") String storeStatus);
}
