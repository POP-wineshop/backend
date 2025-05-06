package com.popwine.backend.module.payment.application;

import com.popwine.backend.module.payment.controller.dto.PaymentConfirmRequest;
import com.popwine.backend.module.payment.controller.dto.PaymentConfirmResponse;
import org.springframework.stereotype.Component;

@Component
public interface PgPaymentProcessor {
    PaymentConfirmResponse confirmPayment(PaymentConfirmRequest request);
}
