package com.popwine.backend.module.Cart.controller;

import com.popwine.backend.core.common.ApiResponse;
import com.popwine.backend.module.Cart.controller.dto.CartAddRequest;
import com.popwine.backend.module.Cart.controller.dto.CartResponse;
import com.popwine.backend.module.Cart.domain.application.CartService;
import com.popwine.backend.module.Cart.controller.dto.UpdateQuantityRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/carts")
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;

    // 1. 장바구니에 상품 담기
    @PostMapping
    public ApiResponse<Void> addToCart(@RequestBody CartAddRequest request) {
        cartService.addToCart(request);
        return ApiResponse.success();
    }

    // 2. 장바구니 전체 조회
    @GetMapping
    public ApiResponse<List<CartResponse>> getCartItems() {
        List<CartResponse> cartItems = cartService.getMyCart();
        return ApiResponse.success(cartItems);
    }

    // 3. 장바구니 수량 수정
    @PatchMapping("/{cartItemId}")
    public ApiResponse<Void> updateQuantity(
            @PathVariable Long cartItemId,
            @RequestBody UpdateQuantityRequest request) {
        cartService.updateQuantity(cartItemId, request.getQuantity());
        return ApiResponse.success();
    }

    // 4. 장바구니 상품 삭제
    @DeleteMapping("/{cartItemId}")
    public ApiResponse<Void> deleteCartItem(@PathVariable Long cartItemId) {
        cartService.deleteItem(cartItemId);
        return ApiResponse.success();
    }
}
