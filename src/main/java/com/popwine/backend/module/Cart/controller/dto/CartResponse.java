package com.popwine.backend.module.Cart.controller.dto;

import com.popwine.backend.module.Cart.domain.entity.CartItem;
import com.popwine.backend.module.wine.domain.entity.Wine;
import lombok.AllArgsConstructor;
import lombok.Getter;


@Getter
@AllArgsConstructor
public class CartResponse {
    private Long cartItemId;
    private String wineName;
    private int quantity;
    private int totalPrice;
    private String thumbnail;

    public static CartResponse of(CartItem cart, Wine wine) {
        return new CartResponse(
                cart.getId(),
                wine.getName().getKorean(),
                cart.getQuantity(),
                wine.getPrice().getValue() * cart.getQuantity(),
                wine.getImageUrl()
        );
    }
}


