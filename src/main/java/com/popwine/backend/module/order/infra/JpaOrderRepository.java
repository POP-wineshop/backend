package com.popwine.backend.module.order.infra;

import com.popwine.backend.module.order.domain.entity.Order;
import com.popwine.backend.module.order.domain.enums.Orderstatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;


public interface JpaOrderRepository extends JpaRepository<Order, Long> {
    List<Order> findByUserId(Long userId);

    List<Order> findByUserIdAndOrderstatus(Long userId, Orderstatus orderstatus);
}

