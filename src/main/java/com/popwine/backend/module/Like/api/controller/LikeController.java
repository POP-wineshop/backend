package com.popwine.backend.module.Like.api.controller;

import com.popwine.backend.core.common.ApiResponse;
import com.popwine.backend.module.Like.api.dto.LikeRes;
import com.popwine.backend.module.Like.application.LikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/likes")
public class LikeController {

    private final LikeService likeService;

    @PostMapping("/{wineId}")
    public ApiResponse<LikeRes> toggleLike(Long wineId) {
        LikeRes response = likeService.toggleLike(wineId);
        return ApiResponse.success(response);
    }
    @GetMapping("/{wineId}")
    public ApiResponse<Boolean> isLiked(Long wineId) {
        boolean isLiked = likeService.isLiked(wineId);
        return ApiResponse.success(isLiked);
    }
}
