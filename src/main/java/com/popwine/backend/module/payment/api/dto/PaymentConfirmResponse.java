package com.popwine.backend.module.payment.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
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

    @JsonProperty("orderId") // JSON TOSS 응답에서 orderId를 tossOrderId로 매핑
    private String tossOrderId;
    private String paymentKey;
    private int amount;
    private String status;
    private String approvedAt;

    public Payment toEntity(Long realOrderId) {
        return Payment.builder()
                .orderId(realOrderId)
                .tossOrderId(tossOrderId)
                .paymentKey(paymentKey)
                .amount(amount)
                .status(PaymentStatus.valueOf(status))
                .approvedAt(approvedAt)
                .build();
    }
}
