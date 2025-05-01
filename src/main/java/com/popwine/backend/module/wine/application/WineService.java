package com.popwine.backend.module.wine.application;

import com.popwine.backend.module.wine.controller.dto.WineRequestDto;
import com.popwine.backend.module.wine.domain.entity.Category;
import com.popwine.backend.module.wine.domain.enums.CategoryType;
import com.popwine.backend.module.wine.domain.repository.CategoryRepository;
import com.popwine.backend.module.wine.domain.repository.WineRepository;
import com.popwine.backend.module.wine.controller.dto.WineResponseDto;
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
    public List<WineResponseDto> getWinesByCategory(List<Long> categoryIds,String keyword) {
        return wineRepository.findByCategoryAndNameFilters(categoryIds, keyword)
                .stream()
                .map(WineResponseDto::from)
                .collect(Collectors.toList());
    }

    //3. 와인 ID통해 상세정보 조회
    public WineResponseDto getWineById(Long id) {
        return wineRepository.findById(id)
                .map(WineResponseDto::from)
                .orElseThrow(() -> new IllegalArgumentException("해당하는 와인을 찾을 수 없다: " + id));
    }

    //4. 와인 등록 (관리자용)
    @Transactional
    public WineResponseDto createWine(WineRequestDto dto) {
        Wine wine = dto.toEntity();

        Category countryCategory = getOrCreateCategory(dto.getCountry(), CategoryType.COUNTRY);
        Category regionCategory = getOrCreateCategory(dto.getRegion(), CategoryType.REGION);
        Category wineTypeCategory = getOrCreateCategory(dto.getWineType().name(), CategoryType.WINE_TYPE);

        wine.addCategory(countryCategory);
        wine.addCategory(regionCategory);
        wine.addCategory(wineTypeCategory);

        Wine savedWine = wineRepository.save(wine);
        return WineResponseDto.from(savedWine);
    }

    private Category getOrCreateCategory(String name, CategoryType type) {
        return categoryRepository.findByNameAndType(name, type)
                .orElseGet(() -> categoryRepository.save(new Category(name, type)));
    }

}

