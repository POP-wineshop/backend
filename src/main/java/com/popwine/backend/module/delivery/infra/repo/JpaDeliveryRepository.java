package com.popwine.backend.module.delivery.infra.repo;

import com.popwine.backend.module.delivery.domain.entity.Delivery;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaDeliveryRepository extends JpaRepository<Delivery, Long> {
    // 기본 배송지 조회
    Delivery findByUserIdAndIsDefaultTrue(Long userId);

    // 배송지 삭제 상태만 변경
    void deleteById(Long id);
}
