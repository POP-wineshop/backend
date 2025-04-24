package com.popwine.backend.module.wine.controller;


import com.popwine.backend.module.wine.domain.entity.Wine;
import com.popwine.backend.module.wine.domain.vo.TasteProfile;
import com.popwine.backend.module.wine.domain.enums.WineType;
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
                wine.getName().name(),
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
