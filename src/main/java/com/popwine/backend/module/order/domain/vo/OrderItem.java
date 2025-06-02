package com.popwine.backend.module.order.domain.vo;

import com.popwine.backend.module.wine.domain.entity.Wine;
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


    // 수량 업데이트 메서드
    public OrderItem changeQuantity(int newQuantity) {
        return OrderItem.builder()
                .wineId(this.wineId)
                .wineNameKor(this.wineNameKor)
                .winePrice(this.winePrice)
                .orderedQuantity(OrderedQuantity.of(newQuantity))
                .orderedPrice(OrderedPrice.of(this.winePrice, OrderedQuantity.of(newQuantity)))
                .wineImageUrl(this.wineImageUrl)
                .build();
    }

    // 주문 아이템 생성 메서드
    public static OrderItem of(Wine wine, int quantity) {
        int winePrice = wine.getPrice().getValue();
        OrderedQuantity orderedQuantity = OrderedQuantity.of(quantity);
        OrderedPrice orderedPrice = OrderedPrice.of(winePrice, orderedQuantity);

        return OrderItem.builder()
                .wineId(wine.getId())
                .wineNameKor(wine.getName().getKorean())
                .winePrice(winePrice)
                .orderedQuantity(orderedQuantity)
                .orderedPrice(orderedPrice)
                .wineImageUrl(wine.getImageUrl())
                .build();
    }


}
