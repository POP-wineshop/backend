package com.popwine.backend.module.payment.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
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
    private String orderId; // 주문 ID
    private int amount;


    public static PaymentConfirmRequest from(Order order, Payment payment) {
        return new PaymentConfirmRequest(
                payment.getPaymentKey(),
                order.getTossOrderId(), // 주문 ID는 Toss에서 발급받은 주문 키
                payment.getAmount()
        );
    }
}