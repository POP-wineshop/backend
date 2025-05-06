package com.popwine.backend.module.payment.infrastructure;

import com.popwine.backend.module.payment.domain.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaPaymentRepository extends JpaRepository<Payment, Long> {
    }
