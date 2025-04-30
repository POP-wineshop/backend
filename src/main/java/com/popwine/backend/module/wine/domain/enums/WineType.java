package com.popwine.backend.module.wine.domain.enums;

import lombok.Getter;

@Getter
public enum WineType {
    RED("Red"),
    WHITE("White"),
    ROSE("Rose"),
    SPARKLING("Sparkling"),
    DESSERT("Dessert");

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
        throw new IllegalArgumentException("해당하는 와인타입을 찾을 수 없다.");
    }
    }
