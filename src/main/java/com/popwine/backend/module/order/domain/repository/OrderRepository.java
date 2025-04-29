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
    List<Order> findByOrderstatus(Orderstatus orderstatus);

}
