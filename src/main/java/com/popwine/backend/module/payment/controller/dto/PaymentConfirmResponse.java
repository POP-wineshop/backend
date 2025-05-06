package com.popwine.backend.module.payment.controller.dto;

import com.popwine.backend.module.payment.domain.entity.Payment;
import com.popwine.backend.module.payment.domain.enums.PaymentStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PaymentConfirmResponse {
    private String orderId;
    private String paymentKey;
    private int amount;
    private String status;
    private String approvedAt;


    public Payment toEntity(Long orderId) {
        return Payment.builder()
                .orderId(orderId)
                .paymentKey(paymentKey)
                .amount(amount)
                .status(PaymentStatus.valueOf(status))
                .approvedAt(approvedAt)
                .build();
    }
}
