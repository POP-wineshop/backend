package com.popwine.backend.module.Cart.domain.entity;

import com.popwine.backend.core.common.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class CartItem extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private Long userId;

    private Long wineId;

    private int quantity;


    public void updateQuantity(int quantity) {
        this.quantity = quantity;
    }
}
