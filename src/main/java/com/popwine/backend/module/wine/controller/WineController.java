package com.popwine.backend.module.wine.controller;

import com.popwine.backend.core.common.ApiResponse;
import com.popwine.backend.module.wine.application.WineService;
import com.popwine.backend.module.wine.controller.dto.WineResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/wines")
@RequiredArgsConstructor
public class WineController {

    private final WineService wineService;

    //1. 모든 와인 조회
    @GetMapping
    public ApiResponse<List<WineResponseDto>> getAllWines() {
        return ApiResponse.success(wineService.getAllWines());
    }

    //2. 카테고리 와인 조회
    @GetMapping("/search")
    public ApiResponse<List<WineResponseDto>> searchWines(
            @RequestParam(required = false) String country,
            @RequestParam(required = false) String region,
            @RequestParam(required = false) String wineType,
            @RequestParam(required = false) String keyword
    ) {
        return ApiResponse.success(
                wineService.searchWines(country, region, wineType, keyword)
        );
    }

    //3. 와인 상세 정보 조회
    @GetMapping("/{id}")
    public ApiResponse<WineResponseDto> getWineById(@PathVariable Long id) {
        return ApiResponse.success(wineService.getWineById(id));
    }


}


