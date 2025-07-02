package com.popwine.backend.module.payment.domain.repo;

import com.popwine.backend.module.payment.domain.entity.Payment;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentRepository {
    void save(Payment payment);
}
