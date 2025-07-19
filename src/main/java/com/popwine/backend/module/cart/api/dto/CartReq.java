package com.popwine.backend.module.cart.api.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CartReq {
    private Long wineId;
    private int quantity;

    @Builder
    public CartReq(Long wineId, int quantity) {
        this.wineId = wineId;
        this.quantity = quantity;
    }
}
