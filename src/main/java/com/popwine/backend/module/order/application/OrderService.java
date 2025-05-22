package com.popwine.backend.module.order.application;

import com.popwine.backend.core.exception.BadRequestException;
import com.popwine.backend.module.order.controller.dto.OrderRequestDto;
import com.popwine.backend.module.order.controller.dto.OrderResponse;
import com.popwine.backend.module.order.domain.entity.Order;
import com.popwine.backend.module.order.domain.enums.Orderstatus;
import com.popwine.backend.module.order.domain.repository.OrderRepository;
import com.popwine.backend.module.order.domain.vo.OrderItem;
import com.popwine.backend.module.order.domain.vo.OrderedPrice;
import com.popwine.backend.module.order.domain.vo.OrderedQuantity;
import com.popwine.backend.module.wine.domain.entity.Wine;
import com.popwine.backend.module.wine.domain.repository.WineRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final WineRepository wineRepository;

    // 1. 장바구니에 상품 담기 (Order 생성)
    @Transactional
    public OrderResponse createOrder(OrderRequestDto request) {

        List<OrderItem> orderItems = request.getOrderItems().stream()
                .map(itemRequest -> {
                    Wine wine = wineRepository.findById(itemRequest.getWineId())
                            .orElseThrow(() -> new IllegalArgumentException("와인 정보가 없습니다."));

                    int winePrice = wine.getPrice().getValue();
                    OrderedQuantity quantity = OrderedQuantity.of(itemRequest.getQuantity());
                    OrderedPrice orderedPrice = OrderedPrice.of(winePrice, quantity);

                    return OrderItem.builder()
                            .wineId(wine.getId())
                            .wineNameKor(wine.getName().getKorean())
                            .winePrice(winePrice)
                            .orderedQuantity(quantity)
                            .orderedPrice(orderedPrice)
                            .wineImageUrl(wine.getImageUrl())
                            .build();
                })
                .collect(Collectors.toList());

        Order order = Order.builder()
                .orderstatus(Orderstatus.PENDING) // 장바구니 상태
                .orderItems(orderItems)
                .build();

        Order savedOrder = orderRepository.save(order);
        return OrderResponse.from(savedOrder);
    }


    // 2. 장바구니 상품 조회
    @Transactional(readOnly = true)
    public List<OrderResponse> getPendingOrders() {
        return orderRepository.findByOrderstatus(Orderstatus.PENDING)
                .stream()
                .map(OrderResponse::from)
                .collect(Collectors.toList());
    }


    // 3. 장바구니 상품 수량 수정
    @Transactional
    public void updateOrderItemQuantity(Long orderId, Long wineId, int newQuantity) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new BadRequestException("주문이 존재하지 않습니다."));

        order.updateItemQuantity(wineId, newQuantity);
    }

    // 4. 장바구니 상품 삭제 (여기는 장바구니상태라 그냥 삭제 해도 괜찮)
    @Transactional
    public void deleteOrderItem(Long orderId, Long wineId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new BadRequestException("주문이 존재하지 않습니다."));

        order.deleteItem(wineId);
    }

    // 5. 장바구니 전체 삭제
    @Transactional
    public void deleteOrder(Long orderId) {
        orderRepository.deleteById(orderId);
    }

    // 6. 결제 된 건에 대한 주문 취소 처리(soft delete)
    @Transactional
    public void cancelOrder(Long orderId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new BadRequestException("주문이 존재하지 않습니다."));
        order.cancel();
    }
}
