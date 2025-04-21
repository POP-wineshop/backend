package com.popwine.module.wine.domain.vo;

import lombok.Getter;

@Getter
public enum WineType {
    RED("Red Wine"),
    WHITE("White Wine"),
    ROSE("Rose Wine"),
    SPARKLING("Sparkling Wine");

    private final String value;

    WineType(String value) {
        this.value = value;
    }

    public static WineType from(String value) {
        for (WineType color : WineType.values()) {
            if (color.getValue().equalsIgnoreCase(value)) {
                return color;
            }

        }
        throw new IllegalArgumentException("설명에 해당하는 색상을 찾을 수 없습니다.");
    }
}
