package com.popwine.backend.module.wine.domain.entity;

import com.popwine.backend.core.common.BaseTimeEntity;
import com.popwine.backend.core.exception.BadRequestException;
import com.popwine.backend.module.wine.domain.vo.Price;
import com.popwine.backend.module.wine.domain.enums.WineType;
import com.popwine.backend.module.wine.domain.vo.TasteProfile;
import com.popwine.backend.module.wine.domain.vo.WineName;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "wine")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@AllArgsConstructor
public class Wine extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //와인 이름
    @Embedded
    private WineName name;

    //와인 가격
    @Embedded
    private Price price;

    //와인 빈티지
    private int vintage;

    //와인 원산지
    private String country;

    //와인 포도 품종
    private String grapeVariety;

    //와인 종류
    @Enumerated(EnumType.STRING)
    private WineType wineType;

    //와인 지역
    private String region;

    //와인 제조사
    private String winery;

    //와인 알콜 도수
    private double alcoholContent;

    //와인 테이스팅노트
    private String tastingNote;

    //와인 페어링
    private String foodPairing;

    //와인 설명
    private String description;

    @Embedded//당도, 산도, 바디
    private TasteProfile tasteProfile;

    //와인 이미지 URL
    private String imageUrl;

    //와인 재고
    private int stock;



    @Builder.Default
    @OneToMany(mappedBy = "wine", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<WineCategory> wineCategories = new ArrayList<>();

    public void addCategory(Category category) {
        this.wineCategories.add(new WineCategory(this, category));
    }


    //재고 차감
    public void decreaseStock(int quantity) {
        if (this.stock < quantity) {
            throw new BadRequestException("재고가 부족합니다.");
        }
        this.stock -= quantity;
    }

}

