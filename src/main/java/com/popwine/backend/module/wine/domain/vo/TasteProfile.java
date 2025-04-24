package com.popwine.backend.module.wine.domain.vo;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Getter
@NoArgsConstructor
public class TasteProfile {
    private int sweetness;
    private int acidity;
    private int body;

    public TasteProfile(int sweetness, int acidity,  int body) {
        this.sweetness = sweetness;
        this.acidity = acidity;
        this.body = body;
    }
}