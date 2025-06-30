package com.popwine.backend.module.payment.api.dto;

import com.popwine.backend.module.order.domain.entity.Order;
import com.popwine.backend.module.payment.domain.entity.Payment;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PaymentConfirmRequest {
    private String paymentKey;
    private String tossOrderId; // 주문 ID
    private String amount;


    public static PaymentConfirmRequest from(Order order, Payment payment) {
        return new PaymentConfirmRequest(
                payment.getPaymentKey(),
                order.getTossOrderId(),
                String.valueOf(payment.getAmount())
        );
    }
}