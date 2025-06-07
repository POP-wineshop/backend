package com.popwine.backend.module.payment.infra.kafka;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.popwine.backend.core.exception.InternalServerException;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaymentEventPublisher {

    private static final String TOPIC = "payment-completed";

    /**
     * 결제 완료 이벤트를 Kafka에 발행하는 서비스
     *
     * @param event 결제 완료 이벤트
     */
    private final KafkaTemplate<String, String> kafkaTemplate;
    private final ObjectMapper objectMapper;

    /**
     * 결제 완료 이벤트를 Kafka에 발행
     *
     * @param event 결제 완료 이벤트
     */
    public void publish(PaymentCompletedEvent event) {
        try {
            String message = objectMapper.writeValueAsString(event);
            kafkaTemplate.send(TOPIC, message);
        } catch (JsonProcessingException e) {
            throw new InternalServerException("Kafka 메시지 직렬화 실패", e);
        }
    }
}
