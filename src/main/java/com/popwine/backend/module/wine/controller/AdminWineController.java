package com.popwine.backend.module.wine.controller;

import com.popwine.backend.core.response.ApiResponse;
import com.popwine.backend.module.wine.application.WineService;
import com.popwine.backend.module.wine.controller.dto.WineRequestDto;
import com.popwine.backend.module.wine.controller.dto.WineResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/admin/wines")
@RequiredArgsConstructor
public class AdminWineController {

    private final WineService wineService;
    //4. 와인 등록 (관리자용)
    @PostMapping
    public ApiResponse<WineResponseDto> createWine(@RequestBody WineRequestDto wineRequestDto) {
        return ApiResponse.success(wineService.createWine(wineRequestDto));
    }
}
