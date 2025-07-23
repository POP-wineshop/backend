package com.popwine.backend.module.Like.domain.repo;

import com.popwine.backend.module.Like.domain.entity.Like;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LikeRepo {
    //와인 좋아요 저장
    Like saveLike(Long wineId, Long userId);

    //와인 좋아요 삭제
    void deleteLike(Long wineId, Long userId);

    //와인 좋아요 여부 확인
    boolean isLiked(Long wineId, Long userId);

    //와인 좋아요 개수 조회
    int countLikes(Long wineId);

    // 사용자 ID와 와인 ID로 좋아요 조회
    Optional<Like> findByUserIdAndWineId(Long userId, Long wineId);
}
