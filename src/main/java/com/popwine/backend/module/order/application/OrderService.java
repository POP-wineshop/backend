package com.popwine.backend.module.order.application;

import com.popwine.backend.core.exception.BadRequestException;
import com.popwine.backend.core.security.util.SecurityUtil;
import com.popwine.backend.module.cart.domain.entity.CartItem;
import com.popwine.backend.module.cart.domain.repo.CartRepo;
import com.popwine.backend.module.order.api.dto.InstantOrderRequestDto;
import com.popwine.backend.module.order.api.dto.OrderResponse;
import com.popwine.backend.module.order.domain.entity.Order;
import com.popwine.backend.module.order.domain.enums.Orderstatus;
import com.popwine.backend.module.order.domain.repo.OrderRepository;
import com.popwine.backend.module.order.domain.vo.OrderItem;
import com.popwine.backend.module.order.infra.kafka.OrderEventPublisher;
import com.popwine.backend.module.wine.domain.entity.Wine;
import com.popwine.backend.module.wine.domain.repo.WineRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final WineRepo wineRepo;
    private final CartRepo cartRepo;
    private final OrderEventPublisher orderEventPublisher;


    @Transactional
    public OrderResponse createOrderFromCart() {
        Long userId = SecurityUtil.getCurrentUserId();

        // 1-1. 해당 유저의 장바구니 조회
        List<CartItem> cartItems = cartRepo.findByUserId(userId);
        if (cartItems.isEmpty()) {
            throw new BadRequestException("장바구니가 비어있습니다.");
        }

        // 1-2. 장바구니의 wineId 리스트 → 와인 정보 가져오기
        List<Long> wineIds = cartItems.stream()
                .map(CartItem::getWineId)
                .collect(Collectors.toList());
        List<Wine> wines = wineRepo.findAllById(wineIds);
        Map<Long, Wine> wineMap = wines.stream()
                .collect(Collectors.toMap(Wine::getId, Function.identity()));


        //1- 3. OrderItem 리스트 생성
        List<OrderItem> orderItems = cartItems.stream()
                .map(item -> {
                    Wine wine = wineMap.get(item.getWineId());
                    if (wine == null) {
                        throw new BadRequestException("장바구니에 유효하지 않은 와인이 포함되어 있습니다. ID: " + item.getWineId());
                    }
                    return OrderItem.of(wine, item.getQuantity());
                })
                .collect(Collectors.toList());

        // 1-4. 주문 생성
        Order order = Order.builder()
                .userId(userId)
                .orderstatus(Orderstatus.PENDING)
                .orderItems(orderItems)
                .tossOrderId(UUID.randomUUID().toString())
                .build();

        Order saved = orderRepository.save(order);

        return OrderResponse.from(saved);
    }


    // 2. 단일 상품 즉시 주문 생성
    @Transactional
    public OrderResponse createInstantOrder(InstantOrderRequestDto request) {
        Long userId = SecurityUtil.getCurrentUserId();

        Wine wine = wineRepo.findById(request.getWineId())
                .orElseThrow(() -> new BadRequestException("와인 정보가 없습니다."));

        OrderItem orderItem = OrderItem.of(wine, request.getQuantity());

        Order order = Order.create(userId, List.of(orderItem));
        Order savedOrder = orderRepository.save(order);

        return OrderResponse.from(savedOrder);
    }

    //3. 주문 전체 조회
    @Transactional(readOnly = true)
    public List<OrderResponse> getMyOrders() {
        Long userId = SecurityUtil.getCurrentUserId();

        List<Order> orders = orderRepository.findByUserId(userId);
        return orders.stream()
                .map(OrderResponse::from)
                .collect(Collectors.toList());
    }
//3-1. 주문 상세 조회
@Transactional(readOnly = true)
public OrderResponse getOrderDetail(Long orderId) {
    Long userId = SecurityUtil.getCurrentUserId();

    Order order = orderRepository.findById(orderId)
            .orElseThrow(() -> new BadRequestException("해당 주문이 존재하지 않습니다."));

    if (!order.getUserId().equals(userId)) {
        throw new BadRequestException("해당 주문에 접근할 수 없습니다.");
    }

    return OrderResponse.from(order);
}

    //4. 결제 된 건에 대한 주문 취소 처리
    @Transactional
    public void cancelOrder(Long orderId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new BadRequestException("주문이 존재하지 않습니다."));
        order.cancel();
    }


    //5. 결제 완료된 주문 상태 변경 및 이벤트 발행
    @Transactional()
    public void completeOrder(Long orderId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new BadRequestException("주문 정보가 없습니다."));
        order.complete();
        orderRepository.save(order);
        orderEventPublisher.publish(order.toEvent());
    }


    @Transactional
    public Order completeOrderWithoutKafka(Long orderId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new BadRequestException("주문 정보가 없습니다."));

        order.complete();
        orderRepository.save(order);

        // Kafka 발행 X
        return order;
    }

    // 장바구니에서 '여러 개 선택'해서 주문
    @Transactional
    public OrderResponse createOrderFromSelectedCartItems(List<Long> cartItemIds) {
        Long userId = SecurityUtil.getCurrentUserId();

        List<CartItem> cartItems = cartRepo.findAllById(cartItemIds);
        if (cartItems.isEmpty()) {
            throw new BadRequestException("선택된 장바구니 항목이 없습니다.");
        }

        // 내 장바구니인지 확인
        for (CartItem item : cartItems) {
            if (!item.getUserId().equals(userId)) {
                throw new BadRequestException("다른 사용자의 장바구니 항목은 주문할 수 없습니다.");
            }
        }

        // 와인 정보 매핑
        List<Long> wineIds = cartItems.stream()
                .map(CartItem::getWineId)
                .toList();
        Map<Long, Wine> wineMap = wineRepo.findAllById(wineIds).stream()
                .collect(Collectors.toMap(Wine::getId, Function.identity()));

        // 주문 항목 생성
        List<OrderItem> orderItems = cartItems.stream()
                .map(item -> {
                    Wine wine = wineMap.get(item.getWineId());
                    return OrderItem.of(wine, item.getQuantity());
                })
                .toList();

        Order order = Order.create(userId, orderItems);
        Order savedOrder = orderRepository.save(order);

        return OrderResponse.from(savedOrder);
    }



}

