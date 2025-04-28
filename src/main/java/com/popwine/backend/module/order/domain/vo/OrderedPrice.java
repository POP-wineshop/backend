package com.popwine.backend.module.order.domain.vo;


import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class OrderedPrice {

    private int value;

    public OrderedPrice(int winePrice, OrderedQuantity quantity) {

        if (winePrice < 0 || quantity.getValue() <= 0) {
            throw new IllegalArgumentException("가격과 수량은 0보다 작을 수 없습니다.");
        }
        this.value = winePrice * quantity.getValue();
        }

    public static OrderedPrice of(int winePrice, OrderedQuantity quantity) {
        return new OrderedPrice(winePrice, quantity);
    }

}
