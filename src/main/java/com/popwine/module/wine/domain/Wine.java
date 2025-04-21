package com.popwine.module.wine.domain;

import com.popwine.module.wine.domain.vo.Price;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Wine {
    @Id
    private Long id;

    private String name;


    @Embedded
    private Price price;

    private int vintage;

    private String origin;

    @ElementCollection
    @CollectionTable(name = "wine_category", joinColumns = @JoinColumn(name = "wine_id"))
    @Column(name = "category_id")
    private List<Long> categoryIDs;
    private String grapeVariety;

    private String region;
    private String winery;
    private String alcoholContent;
    private String tastingNote;
    private String foodPairing;
    private String description;
    private String color;
    private String sweetness;
    private String acidity;
    private String tannin;
    private String body;
    private String imageUrl;

    public void addCategory(Long categoryId) {
        if (categoryId == null) {
            throw new IllegalArgumentException("카테고리 ID는 null일 수 없습니다.");
        }
        this.categoryIDs.add(categoryId);
    }
}
