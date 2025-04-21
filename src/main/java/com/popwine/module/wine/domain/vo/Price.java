package com.popwine.module.wine.domain.vo;

import lombok.Getter;

@Getter
public class Price {
    int value;

    public Price(int value) {
        if (value < 0) {
            throw new IllegalArgumentException("가격은 0보다 작을 수 없습니다.");
        }
        this.value = value;
    }
}
