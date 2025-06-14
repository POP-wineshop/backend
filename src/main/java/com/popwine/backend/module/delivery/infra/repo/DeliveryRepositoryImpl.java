package com.popwine.backend.module.delivery.infra.repo;

import com.popwine.backend.module.delivery.domain.entity.Delivery;
import com.popwine.backend.module.delivery.domain.repository.DeliveryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class DeliveryRepositoryImpl implements DeliveryRepository {

    private final JpaDeliveryRepository jpa;

    // 배송지 등록
    @Override
    public Delivery save(Delivery delivery) {
        return jpa.save(delivery);
    }


    // 배송지 조회
    @Override
    public Delivery findById(Long id) {
        return jpa.findById(id).orElse(null);
    }

    // 배송지 수정
    @Override
    public void update(Delivery delivery) {
        Delivery existingDelivery = jpa.findById(delivery.getId()).orElse(null);
        if (existingDelivery != null) {
            existingDelivery.update(delivery);
            jpa.save(existingDelivery);
        }

    }

    // 배송지 삭제 상태만 변경
    @Override
    public void delete(Long id) {
        jpa.deleteById(id);

    }

    // 기본 배송지 조회
    @Override
    public Delivery findDefaultDeliveryByUserId(Long userId) {
        return jpa.findByUserIdAndIsDefaultTrue(userId);
    }

    // 기본 배송지 여부를 초기화
    @Override
    public void resetDefaultAddressForUser(Long userId) {
        jpa.resetDefaultAddressForUser(userId);
    }
}
