package com.popwine.backend.module.delivery.infra.repo;

import com.popwine.backend.module.delivery.domain.entity.Delivery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface JpaDeliveryRepository extends JpaRepository<Delivery, Long> {
    // 기본 배송지 조회
    Delivery findByUserIdAndIsDefaultTrue(Long userId);

    // 배송지 삭제 상태만 변경
    void deleteById(Long id);

    @Modifying
    @Query("UPDATE Delivery d SET d.isDefault = false WHERE d.userId = :userId AND d.isDefault = true")
    void resetDefaultAddressForUser(@Param("userId") Long userId);

}
