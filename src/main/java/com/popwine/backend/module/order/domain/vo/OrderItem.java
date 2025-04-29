package com.popwine.backend.module.order.domain.vo;

import jakarta.persistence.Column;
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
    @Column(name = "wine_name_kor")
    private String wineNameKor;

    //개별 와인 가격
    @Column(name = "wine_price")
    private int winePrice;

    //주문 수량
    private OrderedQuantity orderedQuantity;

    //총 주문 금액
    private OrderedPrice orderedPrice;

    //와인 이미지 URL
    private String wineImageUrl;


    // ✅ 수량 업데이트 메서드
    public void updateQuantity(int quantity) {
        this.orderedQuantity = OrderedQuantity.of(quantity);
        this.orderedPrice = OrderedPrice.of(this.winePrice, this.orderedQuantity);
    }
}
