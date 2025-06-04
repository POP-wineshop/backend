package com.popwine.backend.module.cart.controller.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CartAddRequest {
    private Long wineId;
    private int quantity;

    @Builder
    public CartAddRequest(Long wineId, int quantity) {
        this.wineId = wineId;
        this.quantity = quantity;
    }
}
