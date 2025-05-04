package com.popwine.backend.module.payment.controller.dto;

import lombok.Getter;

@Getter
public class PaymentConfirmRequest {
    private String paymentKey;
    private String orderId;
    private String amount;
    // getter/setter
}
