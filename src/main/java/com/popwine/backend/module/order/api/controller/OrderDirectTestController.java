package com.popwine.backend.module.order.api.controller;

import com.popwine.backend.core.common.ApiResponse;
import com.popwine.backend.module.order.application.OrderService;
import com.popwine.backend.module.order.domain.entity.Order;
import com.popwine.backend.module.wine.application.WineService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test/orders-direct")
@RequiredArgsConstructor
public class OrderDirectTestController {

    private final OrderService orderService;
    private final WineService wineService; // 재고 차감 직접 호출

    @PostMapping("/{orderId}/complete-direct")
    public ApiResponse<Void> completeOrderWithoutKafka(@PathVariable Long orderId) {
        Order order = orderService.completeOrderWithoutKafka(orderId); // Kafka 없는 흐름
        wineService.decreaseStockByOrder(orderId); // 직접 재고 차감
        return ApiResponse.success();
    }
}
