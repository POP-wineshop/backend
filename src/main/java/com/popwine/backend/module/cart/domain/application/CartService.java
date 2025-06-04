package com.popwine.backend.module.cart.domain.application;


import com.popwine.backend.core.exception.BadRequestException;
import com.popwine.backend.core.security.util.SecurityUtil;
import com.popwine.backend.module.cart.controller.dto.CartAddRequest;
import com.popwine.backend.module.cart.controller.dto.CartResponse;
import com.popwine.backend.module.cart.domain.entity.CartItem;
import com.popwine.backend.module.cart.domain.repo.CartRepo;
import com.popwine.backend.module.wine.domain.entity.Wine;
import com.popwine.backend.module.wine.domain.repository.WineRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CartService {

    private final CartRepo cartRepo;
    private final WineRepository wineRepository;

    // 장바구니 담기
    @Transactional
    public void addToCart(CartAddRequest request) {
        Long userId = SecurityUtil.getCurrentUserId();
        Optional<CartItem> existing = cartRepo.findByUserIdAndWineId(userId, request.getWineId());

        if (existing.isPresent()) {
            existing.get().updateQuantity(request.getQuantity());
        } else {
            CartItem newItem = CartItem.builder()
                    .userId(userId)
                    .wineId(request.getWineId())
                    .quantity(request.getQuantity())
                    .build();
            cartRepo.save(newItem);
        }
    }

    // 장바구니 조회
    @Transactional(readOnly = true)
    public List<CartResponse> getMyCart() {
        Long userId = SecurityUtil.getCurrentUserId();
        List<CartItem> cartItems = cartRepo.findByUserId(userId);

        List<Long> wineIds = cartItems.stream()
                .map(CartItem::getWineId)
                .collect(Collectors.toList());

        List<Wine> wines = wineRepository.findAllById(wineIds);
        Map<Long, Wine> wineMap = wines.stream()
                .collect(Collectors.toMap(Wine::getId, Function.identity()));

        return cartItems.stream()
                .map(item -> {
                    Wine wine = wineMap.get(item.getWineId());
                    if (wine == null) {
                        throw new BadRequestException("해당 와인 정보를 찾을 수 없습니다.");
                    }
                    return CartResponse.of(item, wine);
                })
                .collect(Collectors.toList());
    }

    // 수량 수정
    @Transactional
    public void updateQuantity(Long cartItemId, int quantity) {
        CartItem item = cartRepo.findById(cartItemId)
                .orElseThrow(() -> new BadRequestException("장바구니 항목이 존재하지 않습니다."));
        item.updateQuantity(quantity);
    }

    // 장바구니 항목 삭제
    @Transactional
    public void deleteItem(Long cartItemId) {
        cartRepo.deleteById(cartItemId);
    }
}
