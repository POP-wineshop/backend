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
public class DeliveryRequestDto {

    private Long userId; // 사용자 ID
    private Long orderId; // 주문 ID
    private String address; // 배송지 주소
    private String detailAddress; // 상세 주소
    private String recipientName; // 수령인 이름
    private String recipientPhoneNumber; // 수령인 전화번호
    private String deliveryMessage; // 배송 메시지
    private boolean isDefault; // 기본 배송지 여부


    // DTO -> Entity 변환
    public Delivery toEntity() {
        return Delivery.builder()
                .userId(userId)
                .orderId(orderId)
                .address(address)
                .detailAddress(detailAddress)
                .recipientName(recipientName)
                .recipientPhoneNumber(recipientPhoneNumber)
                .deliveryMessage(deliveryMessage)
                .isDefault(isDefault)
                .build();

    }
}


//TODO MAP API를 사용할지에 대해서 ?

//TODO 배송지 등록 시, 기본 배송지 여부를 선택할 수 있도록 UI 구현
