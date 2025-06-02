package com.popwine.backend.module.order.infra;

import com.popwine.backend.module.order.domain.entity.Order;
import com.popwine.backend.module.order.domain.enums.Orderstatus;
import com.popwine.backend.module.order.domain.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
@RequiredArgsConstructor
public class OrderRepositoryImpl implements OrderRepository {

    private final JpaOrderRepository jpaOrderRepository;


    //주문 저장
    @Override
    public Order save(Order order) {
        return jpaOrderRepository.save(order);
    }


    //주문 삭제
    @Override
    public void deleteById(Long id) {
        jpaOrderRepository.deleteById(id);
    }

    //주문 전체 조회
    @Override
    public List<Order> findAll() {
        return jpaOrderRepository.findAll();
    }

    //주문 ID로 주문 조회
    @Override
    public Optional<Order> findById(Long id) {
        return jpaOrderRepository.findById(id);
    }

    //주문 상태로 주문 조회
    @Override
    public List<Order> findByUserIdAndOrderstatus(Long userId, Orderstatus orderstatus) {
        return jpaOrderRepository.findByUserIdAndOrderstatus(userId, orderstatus);
    }

    @Override
    public List<Order> findByUserId(Long userId) {
        return jpaOrderRepository.findByUserId(userId);
    }
}