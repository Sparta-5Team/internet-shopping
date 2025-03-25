package com.example.team5project.domain.service;

import com.example.team5project.domain.dto.MallResponseDto;
import com.example.team5project.domain.entity.Mall;
import com.example.team5project.domain.repository.MallRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MallService {

    private final MallRepository mallRepository;

    @Transactional(readOnly = true)
    public List<MallResponseDto> getAllMalls() {
        List<Mall> malls = mallRepository.findAll();

        List<MallResponseDto> mallResponseDtos = new ArrayList<MallResponseDto>();
        for (Mall mall : malls) {
            mallResponseDtos.add(new MallResponseDto(mall));
        }
        return mallResponseDtos;
    }

    @Transactional(readOnly = true)
    public List<MallResponseDto> getMallsByTotalRating(Long totalRating) {
        List<Mall> malls = mallRepository.findByTotalRating(totalRating);

        List<MallResponseDto> mallResponseDtos = new ArrayList<>();
        for (Mall mall : malls) {
            mallResponseDtos.add(new MallResponseDto(mall));
        }
        return mallResponseDtos;
    }

    @Transactional(readOnly = true)
    public List<MallResponseDto> getMallsByStoreStatus(String storeStatus) {
        List<Mall> malls = mallRepository.findByStoreStatus(storeStatus);

        List<MallResponseDto> mallResponseDtos = new ArrayList<>();
        for (Mall mall : malls) {
            mallResponseDtos.add(new MallResponseDto(mall));
        }
        return mallResponseDtos;
    }

    @Transactional(readOnly = true)
    public List<MallResponseDto> getMallsByTotalRatingAndStoreStatus(Long totalRating, String storeStatus) {
        List<Mall> malls = mallRepository.findByTotalRatingAndStoreStatus(totalRating, storeStatus);

        List<MallResponseDto> mallResponseDtos = new ArrayList<>();
        for (Mall mall : malls) {
            mallResponseDtos.add(new MallResponseDto(mall));
        }
        return mallResponseDtos;
    }
}
