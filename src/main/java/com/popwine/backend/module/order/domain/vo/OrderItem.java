package com.popwine.backend.module.order.domain.vo;

import jakarta.persistence.Embeddable;
import lombok.*;

@Embeddable
@Getter
@NoArgsConstructor(access= AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class OrderItem {

    //와인 ID'
    private Long wineId;

    //와인 이름
    private String wineNameKor;

    //개별 와인 가격
    private int winePrice;

    //주문 수량
    private int orderedQuantity;

    //총 주문 금액
    private int orderedPrice;

    //와인 이미지 URL
    private String wineImageUrl;


}
