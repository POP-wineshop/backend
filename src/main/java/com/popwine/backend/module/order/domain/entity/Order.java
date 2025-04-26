package com.popwine.backend.module.order.domain.entity;

import com.popwine.backend.module.order.domain.enums.Orderstatus;
import com.popwine.backend.module.order.domain.vo.OrderedWineInfo;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private Orderstatus orderstatus;



    // 주문자 이름
    private String ordererName;

    // 주문한 와인 정보 (스냅샷)
    @Embedded
    private OrderedWineInfo wineInfo;

    // 주문 시간
    private LocalDateTime orderedAt;

}
