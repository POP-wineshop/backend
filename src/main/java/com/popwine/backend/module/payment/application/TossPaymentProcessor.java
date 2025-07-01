package com.popwine.backend.module.payment.application;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.popwine.backend.module.payment.api.dto.PaymentConfirmRequest;
import com.popwine.backend.module.payment.api.dto.PaymentConfirmResponse;
import com.popwine.backend.module.payment.infra.client.TossPaymentClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class TossPaymentProcessor implements PgPaymentProcessor {

    private final TossPaymentClient tossPaymentClient;
    private final ObjectMapper objectMapper; // 주입 받아야 함!

    @Override
    public PaymentConfirmResponse confirmPayment(PaymentConfirmRequest request) {

        // ✅ 실제로 나가는 JSON을 로그로 확인
        try {
            String json = objectMapper.writeValueAsString(request);
            log.info("[Toss에 보낼 JSON 바디] {}", json);
        } catch (JsonProcessingException e) {
            log.error("결제 요청 JSON 직렬화 실패", e);
        }
        //confirmPayment 메서드에서 TossPaymentClient를 사용하여 결제 승인 요청을 처리
        return tossPaymentClient.confirmPayment(request);
    }
}
