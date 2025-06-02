package com.popwine.backend.module.order.domain.repository;

import com.popwine.backend.module.order.domain.entity.Order;
import com.popwine.backend.module.order.domain.enums.Orderstatus;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrderRepository {
    Order save(Order order);
    void deleteById(Long id);
    List<Order> findAll();
    Optional<Order> findById(Long id);

    //주문 상태로 주문 조회
    List<Order> findByUserIdAndOrderstatus(Long userId, Orderstatus orderstatus);

    List<Order> findByUserId(Long userId);
}
