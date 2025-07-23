package com.popwine.backend.module.Like.infra.repo.winelike;

import com.popwine.backend.module.Like.domain.entity.Like;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface JpaLikeRepoImpl extends JpaRepository<Like, Long> {

    // 사용자 ID와 와인 ID로 좋아요 단건 조회
    Optional<Like> findByUserIdAndWineId(Long userId, Long wineId);

    // 좋아요 개수 조회
    int countByWineId(Long wineId);

    // 좋아요 삭제
    void deleteByUserIdAndWineId(Long userId, Long wineId);

    // 좋아요 여부 확인 (존재 여부만 체크할 때)
    boolean existsByUserIdAndWineId(Long userId, Long wineId);
}
