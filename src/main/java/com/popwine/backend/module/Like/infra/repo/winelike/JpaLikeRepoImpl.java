package com.popwine.backend.module.Like.infra.repo.winelike;

import com.popwine.backend.module.Like.domain.entity.Like;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface JpaLikeRepoImpl extends JpaRepository<Like, Long> {
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
