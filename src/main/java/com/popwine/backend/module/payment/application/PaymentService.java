package com.popwine.backend.module.payment.application;

import com.popwine.backend.module.order.application.OrderService;
import com.popwine.backend.module.order.domain.entity.Order;
import com.popwine.backend.module.order.domain.repository.OrderRepository;
import com.popwine.backend.module.payment.api.dto.PaymentConfirmRequest;
import com.popwine.backend.module.payment.api.dto.PaymentConfirmResponse;
import com.popwine.backend.module.payment.domain.repository.PaymentRepository;
import com.popwine.backend.module.payment.infra.kafka.PaymentCompletedEvent;
import com.popwine.backend.module.payment.infra.kafka.PaymentEventPublisher;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Slf4j
@Service
@RequiredArgsConstructor
public class PaymentService {

    private final PgPaymentProcessor paymentProcessor;
    private final PaymentRepository paymentRepository;
    private final OrderRepository orderRepository;
    private final PaymentEventPublisher paymentEventPublisher;
    private final OrderService orderService;


    @Transactional
    public PaymentConfirmResponse confirmPayment(PaymentConfirmRequest request, Long orderId) {
        // Toss API 연동
        PaymentConfirmResponse response = paymentProcessor.confirmPayment(request);

        // 1. tossOrderId로 Order 찾기
                Order order = orderRepository.findByTossOrderId(request.getTossOrderId())
                        .orElseThrow(() -> new IllegalArgumentException("주문 없음"));

        // 2. 주문 상태 변경은 OrderService에 위임
                orderService.completeOrder(order.getId());

        // 3. 결제 저장
                paymentRepository.save(response.toEntity(order.getId()));

        // 4. 이벤트 발행
                paymentEventPublisher.publish(new PaymentCompletedEvent(order.getId()));

                return response;
            }
}
