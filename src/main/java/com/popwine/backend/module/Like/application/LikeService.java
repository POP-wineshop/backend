package com.popwine.backend.module.Like.application;


import com.popwine.backend.core.security.util.SecurityUtil;
import com.popwine.backend.module.Like.api.dto.LikeRes;
import com.popwine.backend.module.Like.domain.entity.Like;
import com.popwine.backend.module.Like.domain.repo.LikeRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class LikeService {

    private final LikeRepo likeRepo;

    // 와인 좋아요 저장
    public LikeRes toggleLike(Long wineID) {
        Long userId = SecurityUtil.getCurrentUserId();

        Optional<Like> existing = likeRepo.findByUserIdAndWineId(userId, wineID);

        if (existing.isPresent()) {
            // 이미 좋아요가 존재하는 경우, 좋아요 삭제
            likeRepo.deleteLike(wineID, userId);
            return LikeRes.of(wineID, false, likeRepo.countLikes(wineID));
        } else {
            // 좋아요가 없는 경우, 좋아요 추가
            Like newLike = likeRepo.saveLike(wineID, userId);
            return LikeRes.from(newLike);
        }
    }

    public boolean isLiked(Long wineId) {
        Long userId = SecurityUtil.getCurrentUserId();
        return likeRepo.isLiked(wineId, userId);
    }

}
