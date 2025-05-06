package com.popwine.backend.module.payment.infrastructure;

import com.popwine.backend.module.payment.domain.entity.Payment;
import com.popwine.backend.module.payment.domain.repository.PaymentRepository;
import org.springframework.stereotype.Repository;

@Repository
public class PaymentRepositoryImpl implements PaymentRepository {
    @Override
    public void save(Payment payment) {

    }
}
