package com.popwine.backend.module.delivery.domain.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Delivery {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId; // 사용자 ID

    private Long orderId; // 주문 ID

    private String address; // 배송지 주소

    private String detailAddress; // 상세 주소

    private String recipientName; // 수령인 이름

    private String recipientPhoneNumber; // 수령인 전화번호

    private String deliveryMessage; // 배송 메시지

    private boolean isDefault; // 기본 배송지 여부


    public static Delivery of(Long userId, Long orderId, String address, String detailAddress, String recipientName, String recipientPhoneNumber, String deliveryMessage, boolean isDefault) {
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

    // 수정 메서드
    public void update(Delivery delivery) {
        this.address = delivery.getAddress();
        this.detailAddress = delivery.getDetailAddress();
        this.recipientName = delivery.getRecipientName();
        this.recipientPhoneNumber = delivery.getRecipientPhoneNumber();
        this.deliveryMessage = delivery.getDeliveryMessage();
        this.isDefault = delivery.isDefault();
    }
}
