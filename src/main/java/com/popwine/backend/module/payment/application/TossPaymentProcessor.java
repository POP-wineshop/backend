package com.popwine.backend.module.payment.application;

import com.popwine.backend.module.payment.controller.dto.PaymentConfirmRequest;
import com.popwine.backend.module.payment.controller.dto.PaymentConfirmResponse;
import com.popwine.backend.module.payment.infrastructure.client.TossPaymentClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TossPaymentProcessor implements PgPaymentProcessor {

    private final TossPaymentClient tossPaymentClient;

    @Override
    public PaymentConfirmResponse confirmPayment(PaymentConfirmRequest request) {
        //confirmPayment 메서드에서 TossPaymentClient를 사용하여 결제 승인 요청을 처리
        return tossPaymentClient.confirmPayment(request);
    }
}
