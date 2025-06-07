package com.popwine.backend.module.order.api.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class OrderRequestDto {

    private String ordererName;
    private List<OrderItemRequest> orderItems;

    @Getter
    @NoArgsConstructor
    public static class OrderItemRequest {
        private Long wineId;
        private int quantity;
    }
}
