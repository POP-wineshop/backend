package com.popwine.backend.module.payment.infra;

import com.popwine.backend.module.payment.domain.entity.Payment;
import com.popwine.backend.module.payment.domain.repository.PaymentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class PaymentRepositoryImpl implements PaymentRepository {

    private final JpaPaymentRepository jpa;

    @Override
    public void save(Payment payment) {
        jpa.save(payment);
    }
}
