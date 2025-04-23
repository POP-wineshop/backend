package com.popwine.backend.module.wine.controller;

import com.popwine.backend.module.wine.application.WineService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/wines")
@RequiredArgsConstructor
public class WineController {

    private final WineService wineService;

    //1. 모든 와인 조회
    @GetMapping
    public List<WineResponseDto> getAllWines() {
        return wineService.getAllWines();
    }

    //2. 카테고리 와인 조회
    @GetMapping("/category")
    public List<WineResponseDto> findWinesByCategories(String country, String region, String wineType) {
        return wineService.findWinesByCategories(country, region, wineType);
    }
    //3. 와인 상세정보 조회
    @GetMapping("/{id}")
    public WineResponseDto getWineById(@PathVariable Long id) {
        return wineService.getWineById(id);
    }
    }



