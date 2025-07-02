package com.popwine.backend.module.payment.application;

import com.popwine.backend.module.order.application.OrderService;
import com.popwine.backend.module.order.domain.entity.Order;
import com.popwine.backend.module.order.domain.repo.OrderRepository;
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
    public PaymentConfirmResponse confirmPayment(PaymentConfirmRequest request) {

        log.info("[결제 승인 요청] paymentKey={}, tossOrderId={}, amount={}",
                request.getPaymentKey(), request.getOrderId(), request.getAmount());

        // 🔒 널/빈 값 체크
        if (request.getPaymentKey() == null || request.getPaymentKey().isBlank()
                || request.getOrderId() == null || request.getOrderId().isBlank()
                || request.getAmount() <= 0) {
            throw new IllegalArgumentException("결제 승인 요청에 필수 값이 누락되었습니다.");
        }

        // Toss API 연동
        PaymentConfirmResponse response = paymentProcessor.confirmPayment(request);

        // 1. tossOrderId로 Order 찾기
                Order order = orderRepository.findByTossOrderId(request.getOrderId())
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
