package com.popwine.backend.module.wine.domain.entity;


import com.popwine.backend.core.common.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "wine_like")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class WineLike extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    private Long userId; // 좋아요를 누른 사용자의 ID

    private Long wineId; // 좋아요를 누른 와인의 ID
    private boolean isLiked; // 좋아요 여부


    //좋아요 생성
    public static WineLike createLike(Long userId, Long wineId) {
        return WineLike.builder()
                .userId(userId)
                .wineId(wineId)
                .isLiked(true) // 기본적으로 좋아요 상태는 true로 설정
                .build();
    }


}

