package com.popwine.backend.module.payment.domain.enums;

import lombok.Getter;

@Getter
public enum PaymentStatus {
    READY("결제 준비"),
    COMPLETED("결제 완료"),
    FAILED("결제 실패"),
    CANCELLED("결제 취소");

    private final String description;

    PaymentStatus(String description) {
        this.description = description;
    }
}
