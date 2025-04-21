package com.popwine.module.wine.domain.vo;


import lombok.Getter;


@Getter
public enum CategoryType {
    ORIGIN("나라"),
    REGION("지역"),
    GRAPE_VARIETY("포도 품종"),
    WINE_TYPE("와인 타입"),
    WINERY("와이너리");

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
