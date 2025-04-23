package com.popwine.backend.module.wine.domain.vo;

import lombok.Getter;

@Getter
public enum WineType {
    RED("Red"),
    WHITE("White"),
    ROSE("Rose"),
    SPARKLING("Sparkling");

    private final String value;

    WineType(String value) {
        this.value = value;
    }

    public static WineType from(String value) {
        for (WineType wineType : WineType.values()) {
            if (wineType.getValue().equalsIgnoreCase(value)) {
                return wineType;
            }

        }
        throw new IllegalArgumentException("설명에 해당하는 색상을 찾을 수 없습니다.");
    }
}
