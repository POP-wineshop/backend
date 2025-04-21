package com.popwine.module.wine.controller;

import com.popwine.module.wine.application.WineService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
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

    //특정 카테고리 와인 조회(다중 카테고리 가능)
    }



