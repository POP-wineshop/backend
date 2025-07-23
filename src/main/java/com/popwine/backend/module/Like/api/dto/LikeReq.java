package com.popwine.backend.module.Like.api.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class LikeReq {

    private final Long wineId; // 좋아요를 누를 와인의 ID

    public static LikeReq of(Long wineId) {
        return LikeReq.builder()
                .wineId(wineId)
                .build();
    }
}
