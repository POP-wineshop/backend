package com.popwine.module.wine.domain.vo;


import lombok.Getter;


@Getter
public enum CategoryType {
    ORIGIN("ORGIN"),
    REGION("REGION"),
    GRAPE_VARIETY("GRAPE_VARIETY"),
    WINE_TYPE("WINE_TYPE"),
    WINERY("WINERY"),;

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
