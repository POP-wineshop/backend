package com.popwine.module.wine.controller;


import com.popwine.module.wine.domain.Wine;
import com.popwine.module.wine.domain.vo.TasteProfile;
import com.popwine.module.wine.domain.vo.WineType;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class WineResponseDto {
    private final Long id;
    private final String name;
    private final int price;
    private final int vintage;
    private final String origin;
    private final String grapeVariety;
    private final String region;
    private final String imageUrl;
    private final TasteProfile tasteProfile;
    private final WineType wineType;

    public static WineResponseDto from(Wine wine) {
        return new WineResponseDto(
                wine.getId(),
                wine.getName(),
                wine.getPrice().getValue(),
                wine.getVintage(),
                wine.getOrigin(),
                wine.getGrapeVariety(),
                wine.getRegion(),
                wine.getImageUrl(),
                wine.getTasteProfile(),
                wine.getWineType()
        );
    }
}
