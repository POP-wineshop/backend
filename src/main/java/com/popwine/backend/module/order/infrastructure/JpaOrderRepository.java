package com.popwine.backend.module.order.infrastructure;

import com.popwine.backend.module.order.domain.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;


public interface JpaOrderRepository extends JpaRepository<Order, Long> {
}
