package com.popwine.backend.module.payment.api.dto;

import com.popwine.backend.module.payment.domain.entity.Payment;
import com.popwine.backend.module.payment.domain.enums.PaymentStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PaymentConfirmResponse {
    private Long orderId; // 주문 ID
    private String tossOrderId; // 주문 키 (PG사에서 발급받은 키)
    private String paymentKey;
    private int amount;
    private String status;
    private String approvedAt;


    /**
     * 결제 응답을 Payment 엔티티로 변환
     *
     * @param orderId 주문 ID
     * @return Payment 엔티티
     */
    public Payment toEntity(Long orderId) {
        return Payment.builder()
                .orderId(orderId)
                .tossOrderId(tossOrderId)
                .paymentKey(paymentKey)
                .amount(amount)
                .status(PaymentStatus.valueOf(status))
                .approvedAt(approvedAt)
                .build();
    }

}
