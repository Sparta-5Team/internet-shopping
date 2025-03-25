package com.example.team5project.domain.mall.repository;

import com.example.team5project.domain.mall.entity.Mall;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MallRepository extends JpaRepository<Mall, Long> {

    Page<Mall> findByTotalRating(Integer totalRating, Pageable pageable);

    Page<Mall> findByStoreStatus(String storeStatus, Pageable pageable);

    @Query("SELECT m FROM Mall m WHERE (:totalRating IS NULL OR m.totalRating = :totalRating)" + "AND (:storeStatus IS NULL OR m.storeStatus = :storeStatus)")
    Page<Mall> findByTotalRatingAndStoreStatus(@Param("totalRating") Integer totalRating, @Param("storeStatus") String storeStatus, Pageable pageable);
}
