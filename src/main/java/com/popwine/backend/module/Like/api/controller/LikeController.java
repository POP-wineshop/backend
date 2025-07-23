package com.popwine.backend.module.Like.api.controller;

import com.popwine.backend.core.common.ApiResponse;
import com.popwine.backend.module.Like.api.dto.LikeRes;
import com.popwine.backend.module.Like.application.LikeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/likes")
@Slf4j
public class LikeController {

    private final LikeService likeService;

    @PostMapping("/{wineId}")
    public ApiResponse<LikeRes> toggleLike(@PathVariable Long wineId) {
        LikeRes response = likeService.toggleLike(wineId);
        return ApiResponse.success(response);
    }
    @GetMapping("/{wineId}")
    public ApiResponse<Boolean> isLiked(@PathVariable Long wineId) {
        boolean isLiked = likeService.isLiked(wineId);
        return ApiResponse.success(isLiked);
    }
}
