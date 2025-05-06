package com.popwine.backend.module.payment.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PaymentConfirmRequest {
    private String paymentKey;
    private String orderId;
    private String amount;



}
