package com.popwine.backend.module.order.infrastructure;

import com.popwine.backend.module.order.domain.entity.Order;
import com.popwine.backend.module.order.domain.repository.OrderRepository;
import org.springframework.stereotype.Repository;



@Repository
public class OrderRepositoryImpl implements OrderRepository {

    private JpaOrderRepository jpaOrderRepository;

    @Override
    public Order save(Order order) {
        return jpaOrderRepository.save(order);
    }
}
