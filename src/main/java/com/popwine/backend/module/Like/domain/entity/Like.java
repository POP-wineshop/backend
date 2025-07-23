package com.popwine.backend.module.Like.domain.entity;


import com.popwine.backend.core.common.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "like")
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class Like extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    private Long wineId; // 좋아요가 눌린 와인의 ID

    private Long userId; // 좋아요를 누른 사용자의 ID

    @Column(name = "like_count", nullable = false)
    private int likeCount; // 좋아요 개수

    public static Like create(Long userId, Long wineId) {
        Like like = new Like();
        like.userId = userId;
        like.wineId = wineId;
        like.likeCount = 1; // 처음 좋아요를 누를 때는 개수를 1로 설정
        return like;
    }
}

