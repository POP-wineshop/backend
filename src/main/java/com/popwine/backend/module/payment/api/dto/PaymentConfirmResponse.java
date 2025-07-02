package com.popwine.backend.module.payment.api.dto;

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
    private String tossOrderId;  // Toss orderId(UUID) → 여기로만 쓰자
    private String paymentKey;
    private int amount;
    private String status;
    private String approvedAt;

    public Payment toEntity(Long realOrderId) {
        return Payment.builder()
                .orderId(realOrderId) // 실제 DB ID
                .tossOrderId(tossOrderId) // Toss UUID
                .paymentKey(paymentKey)
                .amount(amount)
                .status(PaymentStatus.valueOf(status))
                .approvedAt(approvedAt)
                .build();
    }
}
