package com.popwine.backend.module.payment.application;

import com.popwine.backend.module.order.domain.entity.Order;
import com.popwine.backend.module.order.domain.repository.OrderRepository;
import com.popwine.backend.module.payment.controller.dto.PaymentConfirmRequest;
import com.popwine.backend.module.payment.controller.dto.PaymentConfirmResponse;
import com.popwine.backend.module.payment.domain.entity.Payment;
import com.popwine.backend.module.payment.domain.repository.PaymentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
public class PaymentService {

    private final PgPaymentProcessor paymentProcessor;
    private final PaymentRepository paymentRepository;
    private final OrderRepository orderRepository;


    @Transactional
    //1. 결제 승인 요청 처리
    public PaymentConfirmResponse confirmPayment(PaymentConfirmRequest request, Long orderId) {
        // PGProcessor를 통해 결제 승인 처리
        PaymentConfirmResponse response = paymentProcessor.confirmPayment(request);

        // 응답을 Payment 엔티티로 저장
        Payment payment = response.toEntity(orderId);

        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new IllegalArgumentException("주문 정보가 없습니다."));
        // 주문 상태를 결제 완료로 변경
        order.complete();

        paymentRepository.save(payment);
        return response;
    }
}
