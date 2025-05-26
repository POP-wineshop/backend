package com.popwine.backend.module.order.controller;

import com.popwine.backend.core.common.ApiResponse;
import com.popwine.backend.module.order.application.OrderService;
import com.popwine.backend.module.order.controller.dto.InstantOrderRequestDto;
import com.popwine.backend.module.order.controller.dto.OrderRequestDto;
import com.popwine.backend.module.order.controller.dto.OrderResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    // 1. 장바구니에 상품 담기 (주문 생성)
    @PostMapping
    public ApiResponse<OrderResponse> createOrder(@RequestBody OrderRequestDto request) {
        return ApiResponse.success(orderService.createOrder(request));
    }

    // 2. 장바구니 상품 전체 조회 (PENDING 상태 조회)
    @GetMapping
    public ApiResponse<List<OrderResponse>> getPendingOrders() {
        return ApiResponse.success(orderService.getPendingOrders());
    }

    // 3. 장바구니 상품 수량 수정
    @PatchMapping("/{orderId}/items/{wineId}")
    public ApiResponse<Void> updateOrderItemQuantity(
            @PathVariable Long orderId,
            @PathVariable Long wineId,
            @RequestParam int quantity) {

        orderService.updateOrderItemQuantity(orderId, wineId, quantity);
        return ApiResponse.success();
    }

    // 4. 장바구니 상품 삭제
    @DeleteMapping("/{orderId}/items/{wineId}")
    public ApiResponse<Void> deleteOrderItem(
            @PathVariable Long orderId,
            @PathVariable Long wineId) {

        orderService.deleteOrderItem(orderId, wineId);
        return ApiResponse.success();
    }

    // 5. 장바구니 전체 삭제 (주문 하나 삭제)
    @DeleteMapping("/{orderId}")
    public ApiResponse<Void> deleteOrder(@PathVariable Long orderId) {
        orderService.deleteOrder(orderId);
        return ApiResponse.success();
    }

    //6. 주문 삭제 상태만 변경 (주문 취소)
    @DeleteMapping("/{orderId}/cancel")
    public ApiResponse<Void> cancelOrder(@PathVariable Long orderId) {
        orderService.cancelOrder(orderId);
        return ApiResponse.success();
    }

    //7. 즉시 주문 생성
    @PostMapping("/instant")
    public ApiResponse<OrderResponse> createInstantOrder(@RequestBody InstantOrderRequestDto request) {
        return ApiResponse.success(orderService.createInstantOrder(request));
    }
}



