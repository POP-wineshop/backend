package com.popwine.backend.module.wine.domain.enums;


import com.popwine.backend.core.exception.BadRequestException;
import lombok.Getter;


@Getter
public enum CategoryType {
    COUNTRY("country"),
    REGION("Region"),
    WINE_TYPE("winetype");

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
        throw new BadRequestException("설명에 해당하는 카테고리 타입을 찾을 수 없습니다.");
    }
}
