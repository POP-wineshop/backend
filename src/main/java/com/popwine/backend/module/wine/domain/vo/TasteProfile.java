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
        validate("sweetness", sweetness);
        validate("acidity", acidity);
        validate("body", body);

        this.sweetness = sweetness;
        this.acidity = acidity;
        this.body = body;
    }

    public void validate(String name, int value) {
        if (value<1 || value>5) {
            throw new IllegalArgumentException(name+"는 1 이상 5 이하여야 한다.");
        }
    }
}