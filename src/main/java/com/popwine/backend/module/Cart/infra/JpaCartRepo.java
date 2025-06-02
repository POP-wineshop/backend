package com.popwine.backend.module.Cart.infra;

import com.popwine.backend.module.Cart.domain.entity.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface JpaCartRepo extends JpaRepository<CartItem, Long> {
    Optional<CartItem> findByUserIdAndWineId(Long userId, Long wineId);
    List<CartItem> findByUserId(Long userId);
}