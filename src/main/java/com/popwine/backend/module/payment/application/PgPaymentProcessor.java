package com.popwine.backend.module.payment.application;

import com.popwine.backend.module.payment.controller.dto.PaymentConfirmRequest;
import com.popwine.backend.module.payment.controller.dto.PaymentConfirmResponse;


public interface PgPaymentProcessor {
    PaymentConfirmResponse confirmPayment(PaymentConfirmRequest request);
}
