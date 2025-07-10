package com.popwine.backend.module.order.api.controller;

import com.popwine.backend.core.common.ApiResponse;
import com.popwine.backend.module.order.api.dto.OrderResponse;
import com.popwine.backend.module.order.application.OrderService;
import com.popwine.backend.module.order.api.dto.InstantOrderRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;


    //1. 장바구니에서 주문 생성
    @PostMapping("/from-cart")
    public ApiResponse<OrderResponse> createOrderFromCart() {
        return ApiResponse.success(orderService.createOrderFromCart());
    }

    //2. 즉시 주문 생성
    @PostMapping("/instant")
    public ApiResponse<OrderResponse> createInstantOrder(@RequestBody InstantOrderRequestDto request) {
        return ApiResponse.success(orderService.createInstantOrder(request));
    }

    //3. 주문 전체 조회
    @GetMapping("/my")
    public ApiResponse<List<OrderResponse>> getMyOrders() {
        return ApiResponse.success(orderService.getMyOrders());
    }

    //3-1. 주문 상세 조회
    @GetMapping("/{orderId}")
    public ApiResponse<OrderResponse> getOrderDetail(@PathVariable Long orderId) {
        return ApiResponse.success(orderService.getOrderDetail(orderId));
    }


    //4. 결제 된 건 주문 취소
    @DeleteMapping("/{orderId}/cancel")
    public ApiResponse<Void> cancelOrder(@PathVariable Long orderId) {
        orderService.cancelOrder(orderId);
        return ApiResponse.success();
    }
    //5.장바구니에서 단일 상품만 주문
    @PostMapping("/cart/{cartItemId}")
    public ApiResponse<OrderResponse> createOrderFromCartItem(@PathVariable Long cartItemId) {
        return ApiResponse.success(orderService.createOrderFromSelectedCartItems(List.of(cartItemId)));
}
}
