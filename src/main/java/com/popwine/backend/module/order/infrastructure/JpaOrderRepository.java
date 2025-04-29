package com.popwine.backend.module.order.infrastructure;

import com.popwine.backend.module.order.domain.entity.Order;
import com.popwine.backend.module.order.domain.enums.Orderstatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface JpaOrderRepository extends JpaRepository<Order, Long> {
    List<Order> findByOrderstatus(Orderstatus orderstatus);
}
