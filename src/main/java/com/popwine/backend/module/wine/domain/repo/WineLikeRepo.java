package com.popwine.backend.module.wine.domain.repo;

public interface WineLikeRepo {
    //와인 좋아요 저장
    void saveLike(Long wineId, Long userId);

    //와인 좋아요 삭제
    void deleteLike(Long wineId, Long userId);

    //와인 좋아요 여부 확인
    boolean isLiked(Long wineId, Long userId);

    //와인 좋아요 개수 조회
    int countLikes(Long wineId);
}
