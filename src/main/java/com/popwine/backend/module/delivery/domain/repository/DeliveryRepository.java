package com.popwine.backend.module.delivery.domain.repository;

import com.popwine.backend.module.delivery.domain.entity.Delivery;
import org.springframework.stereotype.Repository;

@Repository
public interface DeliveryRepository {

    // 배송지 등록
    void save(Delivery delivery);

    // 배송지 조회
    Delivery findById(Long id);

    // 배송지 수정
    void update(Delivery delivery);

    // 배송지 삭제 상태만 변경
    void delete(Long id);

    // 기본 배송지 조회
    Delivery findDefaultDeliveryByUserId(Long userId);

}
