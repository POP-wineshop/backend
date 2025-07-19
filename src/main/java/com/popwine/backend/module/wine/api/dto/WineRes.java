package com.popwine.backend.module.wine.api.dto;


import com.popwine.backend.module.wine.domain.entity.Wine;
import com.popwine.backend.module.wine.domain.vo.TasteProfile;
import com.popwine.backend.module.wine.domain.enums.WineType;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class WineRes {
    private final Long id;
    private final String korName;
    private final String engName;
    private final int price;
    private final int vintage;
    private final String country;
    private final String grapeVariety;
    private final String region;
    private final double alcoholContent;
    private final String imageUrl;
    private final TasteProfile tasteProfile;
    private final WineType wineType;
    private final int stock;
    private final String description;
    private final String tastingNote;

    public static WineRes from(Wine wine) {
        return new WineRes(
                wine.getId(),
                wine.getName().getKorean(),
                wine.getName().getEnglish(),
                wine.getPrice().getValue(),
                wine.getVintage(),
                wine.getCountry(),
                wine.getGrapeVariety(),
                wine.getRegion(),
                wine.getAlcoholContent(),
                wine.getImageUrl(),
                wine.getTasteProfile(),
                wine.getWineType(),
                wine.getStock(),
                wine.getDescription(),
                wine.getTastingNote()
        );
    }
}
