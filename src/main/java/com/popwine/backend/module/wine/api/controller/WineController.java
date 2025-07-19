package com.popwine.backend.module.wine.api.controller;

import com.popwine.backend.core.common.ApiResponse;
import com.popwine.backend.module.wine.api.dto.WineRes;
import com.popwine.backend.module.wine.application.WineService;
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
    public ApiResponse<List<WineRes>> getAllWines() {
        return ApiResponse.success(wineService.getAllWines());
    }

    //2. 카테고리 와인 조회
    @GetMapping("/search")
    public ApiResponse<List<WineRes>> searchWines(
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
    public ApiResponse<WineRes> getWineById(@PathVariable Long id) {
        return ApiResponse.success(wineService.getWineById(id));
    }


}


