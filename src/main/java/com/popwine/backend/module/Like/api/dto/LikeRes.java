package com.popwine.backend.module.Like.api.dto;

import com.popwine.backend.module.Like.domain.entity.Like;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class LikeRes {
    private final Long wineId;
    private final boolean isLiked;
    private final int likeCount;

    public static LikeRes of(Long wineId, boolean isLiked, int likeCount) {
        return LikeRes.builder()
                .wineId(wineId)
                .isLiked(isLiked)
                .likeCount(likeCount)
                .build();
    }

    public static LikeRes from(Like like) {
        return LikeRes.builder()
                .wineId(like.getWineId())
                .isLiked(true) // 좋아요가 존재하므로 true
                .likeCount(like.getLikeCount()) // 좋아요 개수
                .build();
    }
}
