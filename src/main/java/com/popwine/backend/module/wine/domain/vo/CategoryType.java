package com.popwine.backend.module.wine.domain.vo;


import lombok.Getter;


@Getter
public enum CategoryType {
        ORIGIN("Origin"),
    REGION("Region"),
    GRAPE_VARIETY("Grape Variety"),
    WINE_TYPE("Wine Type"),
    WINERY("Winery"),;

    private final String value;

    CategoryType(String value) {
        this.value = value;
    }

    public static CategoryType from(String value) {
        for (CategoryType categoryType : CategoryType.values()) {
            if (categoryType.getValue().equalsIgnoreCase(value)) {
                return categoryType;
            }
        }
        throw new IllegalArgumentException("설명에 해당하는 카테고리 타입을 찾을 수 없습니다.");
    }
}
