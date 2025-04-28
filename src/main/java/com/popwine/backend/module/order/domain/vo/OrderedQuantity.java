package com.popwine.backend.module.order.domain.vo;


import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class OrderedQuantity {

    private int value;

    public OrderedQuantity(int value) {
        if (value <= 0) {
            throw new IllegalArgumentException("수량은 0보다 작거나 같을 수 없습니다.");
        }
        this.value = value;
    }

    public static OrderedQuantity of(int value) {
        return new OrderedQuantity(value);
    }

}
