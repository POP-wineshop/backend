package com.popwine.backend.module.payment.application;

import com.popwine.backend.module.payment.api.dto.PaymentConfirmReq;
import com.popwine.backend.module.payment.api.dto.PaymentConfirmRes;


public interface PgPaymentProcessor {
    PaymentConfirmRes confirmPayment(PaymentConfirmReq request);
}
