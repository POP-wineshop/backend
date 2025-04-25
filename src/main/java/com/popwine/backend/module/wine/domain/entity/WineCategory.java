package com.popwine.backend.module.wine.domain.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "wine_category")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class WineCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 와인 연관관계
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "wine_id", foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private Wine wine;

    // 카테고리 연관관계
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private Category category;

    public WineCategory(Wine wine, Category category) {
        this.wine = wine;
        this.category = category;
    }
}
