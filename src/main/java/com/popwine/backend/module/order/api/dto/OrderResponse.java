package com.popwine.backend.module.order.api.dto;

import com.popwine.backend.module.order.domain.entity.Order;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Builder
@AllArgsConstructor
public class OrderResponse {

    private Long orderId;
    private String tossOrderId;
    private String orderStatus;
    private List<OrderItemResponse> orderItems;
    private int totalPrice;

    public static OrderResponse from(Order order) {
        List<OrderItemResponse> orderItemResponses = order.getOrderItems().stream()
                .map(OrderItemResponse::from)
                .collect(Collectors.toList());


        //총 합계
        int totalPrice = orderItemResponses.stream()
                .mapToInt(OrderItemResponse::getOrderedPrice)
                .sum();

        return OrderResponse.builder()
                .orderId(order.getId())
                .tossOrderId(order.getTossOrderId())
                .orderStatus(order.getOrderstatus().name())
                .orderItems(orderItemResponses)
                .totalPrice(totalPrice)
                .build();
    }
}
