package com.popwine.backend.module.payment.api.dto;

import com.popwine.backend.module.payment.domain.entity.Payment;
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


    public static PaymentConfirmRequest from(Payment payment) {
        return new PaymentConfirmRequest(
                payment.getPaymentKey(),
                payment.getOrderId().toString(),
                String.valueOf(payment.getAmount())
        );
    }
}