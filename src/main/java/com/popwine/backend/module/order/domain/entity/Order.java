package com.popwine.backend.module.order.domain.entity;

import com.popwine.backend.core.common.BaseTimeEntity;
import com.popwine.backend.core.exception.BadRequestException;
import com.popwine.backend.module.order.domain.enums.Orderstatus;
import com.popwine.backend.module.order.domain.vo.OrderItem;
import com.popwine.backend.module.order.infra.kafka.OrderCompletedEvent;
import jakarta.persistence.*;
import lombok.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "orders")
public class Order extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String tossOrderId; // 주문 키 (PG사에서 발급받은 키)


    private Long userId; // 주문자 ID

    //주문 상태
    @Enumerated(EnumType.STRING)
    private Orderstatus orderstatus;



    //TODO 주문자 이름 + 어떤 인증 키를 쓸 건지 추후 정의
//    private String ordererName;

    //주문 아이템
    @ElementCollection
    @CollectionTable(name = "order_item", joinColumns = @JoinColumn(name = "order_id"))
    private List<OrderItem> orderItems = new ArrayList<>();


    // 주문 생성 메서드
    public static Order create(Long userId, List<OrderItem> orderItems) {
        Order order = new Order();
        order.userId = userId;
        order.orderItems = orderItems;
        order.orderstatus = Orderstatus.PENDING; // 초기 상태 PENDING
        order.tossOrderId = UUID.randomUUID().toString(); // 주문 키 생성
        return order;

    }

    // 결제 완료 처리
    public void complete() {
        this.orderstatus = Orderstatus.COMPLETED;
    }

    //결제 취소 처리
    public void cancel() {
        if (this.orderstatus != Orderstatus.PENDING) {
            throw new BadRequestException("결제된 주문만 취소 할 수 있습니다");
        }
        this.orderstatus = Orderstatus.CANCELED;
    }

    // 주문 완료 이벤트로 변환
    public OrderCompletedEvent toEvent() {
        return new OrderCompletedEvent(this.id);
    }

}
