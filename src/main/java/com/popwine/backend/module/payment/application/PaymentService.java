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

        // [1] 테스트용 paymentKey일 경우 Toss API 우회
        if (isFakePaymentKey(request.getPaymentKey())) {
            log.warn("⚠️ 테스트용 결제 요청 감지됨. Toss 연동 생략 후 바로 결제 완료 처리");

            Order order = orderRepository.findById(orderId)
                    .orElseThrow(() -> new IllegalArgumentException("주문 정보가 없습니다."));

            // 주문 상태 변경
            orderService.completeOrder(orderId);

            // 가짜 응답 객체 만들기
            PaymentConfirmResponse fakeResponse = PaymentConfirmResponse.fakeResponse(orderId, order.getTotalPrice());
            // Payment 엔티티 저장
            paymentRepository.save(fakeResponse.toEntity(orderId));

            // Kafka 이벤트 발행
            paymentEventPublisher.publish(new PaymentCompletedEvent(orderId));

            return fakeResponse;
        }

        // [2] 실제 Toss API 연동
        PaymentConfirmResponse response = paymentProcessor.confirmPayment(request);

        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new IllegalArgumentException("주문 정보가 없습니다."));

        orderService.completeOrder(orderId);
        paymentRepository.save(response.toEntity(orderId));
        paymentEventPublisher.publish(new PaymentCompletedEvent(orderId));

        return response;
    }

    private boolean isFakePaymentKey(String key) {
        return key != null && key.startsWith("fake-");
    }
}
