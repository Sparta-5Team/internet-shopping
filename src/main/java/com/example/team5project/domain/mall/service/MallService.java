package com.example.team5project.domain.mall.service;

import com.example.team5project.domain.mall.dto.response.MallResponseDto;
import com.example.team5project.domain.mall.entity.Mall;
import com.example.team5project.domain.mall.repository.MallRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MallService {

    private final MallRepository mallRepository;
    private final int PAGE_SIZE = 10;

    @Transactional(readOnly = true)
    public List<MallResponseDto> getAllMalls() {
        List<Mall> malls = mallRepository.findAll();

        List<MallResponseDto> mallResponseDtos = new ArrayList<>();
        for (Mall mall : malls) {
            mallResponseDtos.add(new MallResponseDto(mall));
        }
        return mallResponseDtos;
    }

    @Transactional(readOnly = true)
    public List<MallResponseDto> getMallsByTotalRating(Integer totalRating, int page) {
        Pageable pageable = PageRequest.of(page, PAGE_SIZE, Sort.by("monitoringDate").descending());
        Page<Mall> malls = mallRepository.findByTotalRating(totalRating, pageable);
        List<Mall> mallList = malls.getContent();

        List<MallResponseDto> mallResponseDtos = new ArrayList<>();
        for (Mall mall : mallList) {
            mallResponseDtos.add(new MallResponseDto(mall));
        }
        return mallResponseDtos;
    }

    @Transactional(readOnly = true)
    public List<MallResponseDto> getMallsByStoreStatus(String storeStatus, int page) {
        Pageable pageable = PageRequest.of(page, PAGE_SIZE, Sort.by("monitoringDate").descending());
        Page<Mall> malls = mallRepository.findByStoreStatus(storeStatus, pageable);
        List<Mall> mallList = malls.getContent();

        List<MallResponseDto> mallResponseDtos = new ArrayList<>();
        for (Mall mall : mallList) {
            mallResponseDtos.add(new MallResponseDto(mall));
        }
        return mallResponseDtos;
    }

    @Transactional(readOnly = true)
    public List<MallResponseDto> getMallsByTotalRatingAndStoreStatus(Integer totalRating, String storeStatus, int page) {
        Pageable pageable = PageRequest.of(page, PAGE_SIZE, Sort.by("monitoringDate").descending());
        Page<Mall> malls = mallRepository.findByTotalRatingAndStoreStatus(totalRating, storeStatus, pageable);
        List<Mall> mallList = malls.getContent();

        List<MallResponseDto> mallResponseDtos = new ArrayList<>();
        for (Mall mall : mallList) {
            mallResponseDtos.add(new MallResponseDto(mall));
        }
        return mallResponseDtos;
    }
}
