package com.popwine.backend.module.Cart.domain.repo;

import com.popwine.backend.module.Cart.domain.entity.CartItem;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

public interface CartRepo {

    CartItem save(CartItem cartItem);

    Optional<CartItem> findByUserIdAndWineId(Long userId, Long wineId);

    List<CartItem> findByUserId(Long userId);

    Optional<CartItem> findById(Long id);

    void deleteById(Long id);
}