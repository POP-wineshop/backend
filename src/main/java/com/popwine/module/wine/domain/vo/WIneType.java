package com.popwine.module.wine.domain.vo;

import lombok.Getter;

@Getter
public enum WIneType {
    RED("Red Wine"),
    WHITE("White Wine"),
    ROSE("Rose Wine"),
    SPARKLING("Sparkling Wine");

    private final String Value;

    WIneType(String value) {
        this.Value = value;
    }

    public static WIneType from(String value) {
        for (WIneType color : WIneType.values()) {
            if (color.getValue().equalsIgnoreCase(value)) {
                return color;
            }

        }
        throw new IllegalArgumentException("설명에 해당하는 색상을 찾을 수 없습니다.");
    }
}
