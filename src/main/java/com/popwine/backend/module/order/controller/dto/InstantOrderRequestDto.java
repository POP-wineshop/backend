package com.popwine.backend.module.order.controller.dto;

import lombok.Getter;

@Getter
public class InstantOrderRequestDto {
    private Long wineId;
    private int quantity;
}
