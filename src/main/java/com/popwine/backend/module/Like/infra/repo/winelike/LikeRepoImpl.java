package com.popwine.backend.module.Like.infra.repo.winelike;

import com.popwine.backend.module.Like.domain.entity.Like;
import com.popwine.backend.module.Like.domain.repo.LikeRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class LikeRepoImpl implements LikeRepo {

    private final JpaLikeRepoImpl jpa;

    // 와인 좋아요 저장
    @Override
    public Like saveLike(Long wineId, Long userId) {
        Like like = Like.create(userId, wineId); // 정적 팩토리 메서드 사용 (or new Like(...))
        return jpa.save(like);
    }

    // 와인 좋아요 삭제
    @Override
    public void deleteLike(Long wineId, Long userId) {
        jpa.deleteByUserIdAndWineId(userId, wineId);
    }

    // 와인 좋아요 여부 확인
    @Override
    public boolean isLiked(Long wineId, Long userId) {
        return jpa.existsByUserIdAndWineId(userId, wineId);
    }

    // 와인 좋아요 개수 조회
    @Override
    public int countLikes(Long wineId) {
        return jpa.countByWineId(wineId);
    }

    // 사용자 ID와 와인 ID로 좋아요 조회
    @Override
    public Optional<Like> findByUserIdAndWineId(Long userId, Long wineId) {
        return jpa.findByUserIdAndWineId(userId, wineId);
    }
}
