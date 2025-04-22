package com.popwine.module.wine.domain;

import com.popwine.module.wine.domain.vo.Price;
import com.popwine.module.wine.domain.vo.TasteProfile;
import com.popwine.module.wine.domain.vo.WineType;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@AllArgsConstructor
public class Wine {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    @Enumerated(EnumType.STRING)
    private WineType wineType;

    private String region;
    private String winery;
    private String alcoholContent;
    private String tastingNote;
    private String foodPairing;
    private String description;

    @Embedded//당도, 산도, 타닌, 바디
    private TasteProfile tasteProfile;

    private String imageUrl;

    public void addCategory(Long categoryId) {
        if (categoryId == null) {
            throw new IllegalArgumentException("카테고리 ID는 null일 수 없습니다.");
        }
        this.categoryIDs.add(categoryId);
    }
}
