package com.popwine.backend.module.wine.controller.dto;

import com.popwine.backend.module.wine.domain.entity.Wine;
import com.popwine.backend.module.wine.domain.enums.WineType;
import com.popwine.backend.module.wine.domain.vo.Price;
import com.popwine.backend.module.wine.domain.vo.TasteProfile;
import com.popwine.backend.module.wine.domain.vo.WineName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WineRequestDto {
    private WineName wineName;
    private Price price;
    private WineType wineType;
    private TasteProfile tasteProfile;
    private int vintage;
    private String country;
    private String region;
    private String grapeVariety;
    private String winery;
    private double alcoholContent;
    private String tastingNote;
    private String foodPairing;

    private String description;
    private String imageUrl;

    private int stock;

    public Wine toEntity() {
        return Wine.builder()
                .name(wineName)
                .price(price)
                .wineType(wineType)
                .tasteProfile(tasteProfile)
                .vintage(vintage)
                .country(country)
                .region(region)
                .grapeVariety(grapeVariety)
                .winery(winery)
                .alcoholContent(alcoholContent)
                .tastingNote(tastingNote)
                .foodPairing(foodPairing)
                .description(description)
                .imageUrl(imageUrl)
                .stock(stock)
                .build();
    }

}
