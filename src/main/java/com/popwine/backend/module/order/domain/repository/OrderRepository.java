package com.popwine.backend.module.order.domain.repository;

import com.popwine.backend.module.order.domain.entity.Order;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository {
    Order save(Order order);
}
