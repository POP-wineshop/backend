package com.popwine.module.wine.domain.vo;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Getter
@NoArgsConstructor
public class TasteProfile {
    private String sweetness;
    private String acidity;
    private String tannin;
    private String body;

    public TasteProfile(String sweetness, String acidity, String tannin, String body) {
        this.sweetness = sweetness;
        this.acidity = acidity;
        this.tannin = tannin;
        this.body = body;
    }
}