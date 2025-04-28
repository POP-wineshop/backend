package com.popwine.backend.module.order.controller.dto;

import com.popwine.backend.module.order.domain.entity.Order;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@AllArgsConstructor
public class OrderResponse {

    private Long orderId;
    private String ordererName;
    private String orderStatus;
    private List<OrderItemResponse> orderItems;

    public static OrderResponse from(Order order) {
        List<OrderItemResponse> orderItemResponses = order.getOrderItems().stream()
                .map(OrderItemResponse::from)
                .collect(Collectors.toList());

        return new OrderResponse(
                order.getId(),
                order.getOrdererName(),
                order.getOrderstatus().name(),
                orderItemResponses
        );
    }
}
