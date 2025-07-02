package com.popwine.backend.module.payment.domain.entity;


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
    private String tossOrderId;
    //걀제 가격
    private int amount;

    @Enumerated(EnumType.STRING)
    private PaymentStatus status;

    private String approvedAt;

}
