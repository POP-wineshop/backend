package com.popwine.backend.module.payment.controller.dto;

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
    private Long orderId;
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
                .paymentKey(paymentKey)
                .amount(amount)
                .status(PaymentStatus.valueOf(status))
                .approvedAt(approvedAt)
                .build();
    }
    /**
     * 테스트용 가짜 결제 응답 생성
     *
     * @param orderId 주문 ID
     * @param amount  결제 금액
     * @return 가짜 PaymentConfirmResponse 객체
     */
    public static PaymentConfirmResponse fakeResponse(Long orderId, int amount) {
        return PaymentConfirmResponse.builder()
                .orderId(orderId)
                .paymentKey("FAKE-PG-KEY")
                .amount(amount)
                .status(PaymentStatus.COMPLETED.name()) // 가짜 결제 상태
                .approvedAt(LocalDateTime.now().toString())
                .build();
    }


}
