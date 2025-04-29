package com.popwine.backend.module.order.domain.vo;


import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class OrderedQuantity {

    @Column(name = "ordered_quantity")
    private int quantity;

    public OrderedQuantity(int quan) {
        if (quan <= 0) {
            throw new IllegalArgumentException("수량은 0보다 작거나 같을 수 없습니다.");
        }
        this.quantity = quan;
    }

    public static OrderedQuantity of(int value) {
        return new OrderedQuantity(value);
    }

}
