package com.popwine.backend.module.cart.domain.repo;

import com.popwine.backend.module.cart.domain.entity.CartItem;

import java.util.List;
import java.util.Optional;

public interface CartRepo {

    CartItem save(CartItem cartItem);

    Optional<CartItem> findByUserIdAndWineId(Long userId, Long wineId);

    List<CartItem> findByUserId(Long userId);

    Optional<CartItem> findById(Long id);

    void deleteById(Long id);

    List<CartItem> findAllById(List<Long> cartItemIds);
}