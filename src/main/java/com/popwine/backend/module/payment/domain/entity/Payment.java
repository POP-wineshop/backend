package com.popwine.backend.module.payment.domain.entity;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.popwine.backend.module.payment.domain.enums.PaymentStatus;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long orderId;
    private String paymentKey;

    @JsonProperty("orderID") // JSON TOSS 응답에서 orderID를 tossOrderId로 매핑
    private String tossOrderId;
    //결제 가격
    private int amount;

    @Enumerated(EnumType.STRING)
    private PaymentStatus status;

    private String approvedAt;

}
