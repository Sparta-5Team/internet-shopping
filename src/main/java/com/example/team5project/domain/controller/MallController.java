package com.example.team5project.domain.controller;

import com.example.team5project.domain.dto.MallResponseDto;
import com.example.team5project.domain.entity.Mall;
import com.example.team5project.domain.service.MallService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class MallController {

    private final MallService mallService;

    @GetMapping("/malls")
    public ResponseEntity<List<MallResponseDto>> getAllMalls() {
        return ResponseEntity.ok(mallService.getAllMalls());
    }

    @GetMapping("/malls/totalRating/{totalRating}")
    public ResponseEntity<List<MallResponseDto>> getMallsByTotalRating(@PathVariable Long totalRating) {
        return ResponseEntity.ok(mallService.getMallsByTotalRating(totalRating));
    }

    @GetMapping("/malls/storeStatus/{storeStatus}")
    public ResponseEntity<List<MallResponseDto>> getMallsByStoreStatus(@PathVariable String storeStatus) {
        return ResponseEntity.ok(mallService.getMallsByStoreStatus(storeStatus));
    }

    @GetMapping("/malls/ratingAndStatus")
    public ResponseEntity<List<MallResponseDto>> getMallsByRatingAndStatus(@RequestParam(required = false) Long totalRating, @RequestParam(required = false) String storeStatus) {
        return ResponseEntity.ok(mallService.getMallsByTotalRatingAndStoreStatus(totalRating, storeStatus));
    }
}
