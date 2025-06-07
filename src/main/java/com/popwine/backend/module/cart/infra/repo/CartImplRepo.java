package com.popwine.backend.module.cart.infra.repo;

import com.popwine.backend.module.cart.domain.entity.CartItem;
import com.popwine.backend.module.cart.domain.repo.CartRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class CartImplRepo implements CartRepo {

    private final JpaCartRepo jpaCartRepo;

    @Override
    public CartItem save(CartItem cartItem) {
        return jpaCartRepo.save(cartItem);
    }

    @Override
    public Optional<CartItem> findByUserIdAndWineId(Long userId, Long wineId) {
        return jpaCartRepo.findByUserIdAndWineId(userId, wineId);
    }

    @Override
    public List<CartItem> findByUserId(Long userId) {
        return jpaCartRepo.findByUserId(userId);
    }

    @Override
    public Optional<CartItem> findById(Long id) {
        return jpaCartRepo.findById(id);
    }

    @Override
    public void deleteById(Long id) {
        jpaCartRepo.deleteById(id);
    }
}
