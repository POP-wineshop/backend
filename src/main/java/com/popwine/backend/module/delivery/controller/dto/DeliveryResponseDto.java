package com.popwine.backend.module.delivery.controller.dto;

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
    private String address; // 배송지 주소
    private String detailAddress; // 상세 주소
    private String recipientName; // 수령인 이름
    private String recipientPhoneNumber; // 수령인 전화번호
    private String deliveryMessage; // 배송 메시지
    private boolean isDefault; // 기본 배송지 여부

    public static DeliveryResponseDto of(Long id, String address, String detailAddress, String recipientName, String recipientPhoneNumber, String deliveryMessage, boolean isDefault) {
        return new DeliveryResponseDto(id, address, detailAddress, recipientName, recipientPhoneNumber, deliveryMessage, isDefault);
    }
}
