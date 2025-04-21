package com.popwine.module.wine.application;

import com.popwine.module.wine.controller.WineResponseDto;
import com.popwine.module.wine.domain.Wine;
import com.popwine.module.wine.domain.repository.CategoryRepository;
import com.popwine.module.wine.domain.repository.WineRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;


@Service
@AllArgsConstructor
public class WineService {
    private final WineRepository wineRepository;
    private final CategoryRepository categoryRepository;


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
    public List<WineResponseDto> findWinesByCategories(List<Long> categoryIds) {
            List<Wine> wines = wineRepository.findByCategoryIdsContainingAny(categoryIds);
        return wines.stream()
                .map(WineResponseDto::from)
                .collect(Collectors.toList());
    }


}

