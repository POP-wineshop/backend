package com.popwine.backend.module.delivery.infra.repo;

import com.popwine.backend.module.delivery.domain.entity.Delivery;
import com.popwine.backend.module.delivery.domain.repository.DeliveryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class DeliveryRepositoryImpl implements DeliveryRepository {

    private final JpaDeliveryRepository jpa;

    @Override
    public Delivery save(Delivery delivery) {
        return jpa.save(delivery);
    }


    @Override
    public Delivery findById(Long id) {
        return jpa.findById(id).orElse(null);
    }

    @Override
    public void update(Delivery delivery) {
        Delivery existingDelivery = jpa.findById(delivery.getId()).orElse(null);
        if (existingDelivery != null) {
            existingDelivery.update(delivery);
            jpa.save(existingDelivery);
        }

    }

    @Override
    public void delete(Long id) {
        jpa.deleteById(id);

    }

    @Override
    public Delivery findDefaultDeliveryByUserId(Long userId) {
        return jpa.findByUserIdAndIsDefaultTrue(userId);
    }
}
