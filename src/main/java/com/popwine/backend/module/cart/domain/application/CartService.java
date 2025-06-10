package com.popwine.backend.module.cart.domain.application;


import com.popwine.backend.core.exception.BadRequestException;
import com.popwine.backend.core.security.util.SecurityUtil;
import com.popwine.backend.module.cart.api.dto.CartAddRequest;
import com.popwine.backend.module.cart.api.dto.CartResponse;
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
