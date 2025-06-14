package com.popwine.backend.module.delivery.api.dto;

import com.popwine.backend.module.delivery.domain.entity.Delivery;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DeliveryResponseDto {

    private Long id; // 배송지 ID
    private String orderId; // 주문 ID (optional, if needed)
    private String address; // 배송지 주소
    private String detailAddress; // 상세 주소
    private String recipientName; // 수령인 이름
    private String recipientPhoneNumber; // 수령인 전화번호
    private String deliveryMessage; // 배송 메시지
    private boolean isDefault; // 기본 배송지 여부


    //Entity -> DTO 변환
    public static DeliveryResponseDto from(Delivery savedDelivery) {
        return DeliveryResponseDto.builder()
                .id(savedDelivery.getId())
                .address(savedDelivery.getAddress())
                .detailAddress(savedDelivery.getDetailAddress())
                .recipientName(savedDelivery.getRecipientName())
                .recipientPhoneNumber(savedDelivery.getRecipientPhoneNumber())
                .deliveryMessage(savedDelivery.getDeliveryMessage())
                .isDefault(savedDelivery.isDefault())
                .build();
    }
}
