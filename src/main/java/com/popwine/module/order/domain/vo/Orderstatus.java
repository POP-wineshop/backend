package com.popwine.module.order.domain.vo;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.BadRequestException;

@Getter
@RequiredArgsConstructor
public enum Orderstatus {
    PENDING("주문 대기중"),
    PROCESSING("처리중"),
    SHIPPED("배송중"),
    DELIVERED("배송완료"),
    CANCELED("취소됨");

    private final String value;

    public static Orderstatus fromString(String value) throws BadRequestException {
        for (Orderstatus orderstatus : Orderstatus.values()) {
            if(orderstatus.value.equalsIgnoreCase(value)) {
                return orderstatus;
            }
        }
        throw new BadRequestException("유효하지 않은 주문 상태: " + value);
    }
}

