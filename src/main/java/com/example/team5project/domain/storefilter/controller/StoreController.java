package com.example.team5project.domain.storefilter.controller;

import com.example.team5project.domain.storefilter.entity.Store;
import com.example.team5project.domain.storefilter.service.StoreService;
import com.example.team5project.domain.storefilter.status.StoreStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/stores")
@RequiredArgsConstructor
public class StoreController {

    private final StoreService storeService;

    @GetMapping
    public ResponseEntity<List<Store>> getStores(
            @RequestParam(required = false) Integer rating,
            @RequestParam(required = false) StoreStatus status) {
        List<Store> stores = storeService.getFilteredStores(rating, status);
        return ResponseEntity.ok(stores);
    }
}
