package com.popwine.backend.module.wine.domain.vo;

import com.popwine.backend.core.exception.BadRequestException;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Getter
@NoArgsConstructor
public class Price {

    @Column(name = "price", nullable = false)
    private int value;

    public Price(int value) {
        if (value < 0) {
            throw new BadRequestException("가격은 0 이상이어야 한다.");
        }
        if (value > 1_000_000_000) {
            throw new BadRequestException("가격은 10억 이하여야 한다.");
        }
        this.value = value;
    }
}
