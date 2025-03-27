package com.example.team5project.domain.storefilter.controller;

import com.example.team5project.domain.storefilter.dto.request.StoreFilterRequest;
import com.example.team5project.domain.storefilter.dto.response.StorePageResponse;
import com.example.team5project.domain.storefilter.service.StoreService;
import com.example.team5project.domain.storefilter.status.StoreStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/stores")
@RequiredArgsConstructor
public class StoreController {

    private final StoreService storeService;

    @GetMapping
    public ResponseEntity<StorePageResponse> getStores(
            @RequestParam(required = false) Integer rating,
            @RequestParam(required = false) StoreStatus status,
            @RequestParam(required = false) Long cursorId,
            @RequestParam(defaultValue = "10") int size) {

        StoreFilterRequest request = new StoreFilterRequest();
        request.setRating(rating);
        request.setStatus(status);
        request.setCursorId(cursorId);
        request.setSize(size);

        StorePageResponse response = storeService.getFilteredStores(request);
        return ResponseEntity.ok(response);
    }
}