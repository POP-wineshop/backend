package com.popwine.backend.module.payment.application;

import com.popwine.backend.module.payment.controller.dto.PaymentConfirmRequest;
import com.popwine.backend.module.payment.controller.dto.PaymentConfirmResponse;
import com.popwine.backend.module.payment.domain.entity.Payment;
import com.popwine.backend.module.payment.domain.repository.PaymentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class PaymentService {

    private final PgPaymentProcessor paymentProcessor;
    private final PaymentRepository paymentRepository;

    public PaymentConfirmResponse confirmPayment(PaymentConfirmRequest request, Long orderId) {
        // PGProcessor를 통해 결제 승인 처리
        PaymentConfirmResponse response = paymentProcessor.confirmPayment(request);

        // 응답을 Payment 엔티티로 저장
        //TODO  (엔티티에 박히는 값인데 application에서 처리하는게 맞는지? )
        //Mapper를 사용하여 변환하는게 좋을지?
        Payment payment = response.toEntity(orderId);

        paymentRepository.save(payment);
        return response;
    }
}
