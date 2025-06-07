package com.popwine.backend.module.payment.application;

import com.popwine.backend.module.payment.api.dto.PaymentConfirmRequest;
import com.popwine.backend.module.payment.api.dto.PaymentConfirmResponse;


public interface PgPaymentProcessor {
    PaymentConfirmResponse confirmPayment(PaymentConfirmRequest request);
}
