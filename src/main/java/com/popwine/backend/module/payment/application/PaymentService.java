package com.popwine.backend.module.payment.application;

import com.popwine.backend.module.payment.controller.dto.PaymentConfirmRequest;
import com.popwine.backend.module.payment.controller.dto.PaymentConfirmResponse;
import com.popwine.backend.module.payment.domain.entity.Payment;
import com.popwine.backend.module.payment.domain.enums.PaymentStatus;
import com.popwine.backend.module.payment.domain.repository.PaymentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class PaymentService {

    private final PgPaymentProcessor paymentProcessor;
    private final PaymentRepository paymentRepository;

    public PaymentConfirmResponse confirmPayment(PaymentConfirmRequest request, Long orderId) {
        // PGProcessor를 통해 결제 승인 처리
        PaymentConfirmResponse response = paymentProcessor.confirmPayment(request);

        // 응답을 Payment 엔티티로 저장
        Payment payment = Payment.builder()
                .orderId(orderId)
                .paymentKey(response.getPaymentKey())
                .amount(response.getAmount())
                .status(PaymentStatus.valueOf(response.getStatus()))
                .approvedAt(response.getApprovedAt())
                .build();

        paymentRepository.save(payment);
        return response;
    }
}
