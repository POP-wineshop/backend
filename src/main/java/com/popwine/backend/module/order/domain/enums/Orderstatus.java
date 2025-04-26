package com.popwine.backend.module.order.domain.enums;

import lombok.Getter;
import org.apache.coyote.BadRequestException;

@Getter
public enum Orderstatus {
    PENDING("주문 대기중"),
    PROCESSING("처리중"),
    SHIPPED("배송중"),
    DELIVERED("배송완료"),
    CANCELED("취소됨");

    private final String value;

    Orderstatus(String value) {
        this.value = value;
    }

    public static Orderstatus fromString(String value) throws BadRequestException {
        if (value == null || value.isEmpty()) {
            throw new BadRequestException("주문 상태는 null 또는 빈 문자열일 수 없습니다.");
        }
        for (Orderstatus orderstatus : Orderstatus.values()) {
            if(orderstatus.value.equalsIgnoreCase(value)) {
                return orderstatus;
            }
        }
        throw new BadRequestException("유효하지 않은 주문 상태: " + value);
    }
}

