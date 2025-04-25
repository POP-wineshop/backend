package com.popwine.backend.module.wine.application;

import com.popwine.backend.module.wine.domain.repository.WineRepository;
import com.popwine.backend.module.wine.controller.WineResponseDto;
import com.popwine.backend.module.wine.domain.entity.Wine;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;


@Service
@AllArgsConstructor
public class WineService {
    private final WineRepository wineRepository;


    //1. 모든 와인 조회
    @Transactional(readOnly = true)
    public List<WineResponseDto> getAllWines() {
        return wineRepository.findAll()
                .stream()
                .map(WineResponseDto::from)
                .collect(Collectors.toList());
    }

    //2. 카테고리 와인 조회
    @Transactional(readOnly = true)
    public List<WineResponseDto> getWinesByCategory(List<Long> categoryIds) {
        return wineRepository.findByCategoryFilters(categoryIds)
                .stream()
                .map(WineResponseDto::from)
                .collect(Collectors.toList());
    }

    //3. 와인 ID통해 상세정보 조회
    public WineResponseDto getWineById(Long id) {
        return wineRepository.findById(id)
                .map(WineResponseDto::from)
                .orElseThrow(() -> new IllegalArgumentException("와인을 찾을 수 없습니다: " + id));
    }
}

