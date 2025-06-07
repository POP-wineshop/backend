package com.popwine.backend.module.order.api.dto;

import com.popwine.backend.module.order.domain.vo.OrderItem;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class OrderItemResponse {

    private Long wineId;
    private String wineNameKor;
    private int winePrice;
    private int orderedQuantity;
    private int orderedPrice;
    private String wineImageUrl;

    public static OrderItemResponse from(OrderItem item) {
        return new OrderItemResponse(
                item.getWineId(),
                item.getWineNameKor(),
                item.getWinePrice(),
                item.getOrderedQuantity().getQuantity(),
                item.getOrderedPrice().getValue(),
                item.getWineImageUrl()
        );
    }
}
