package com.popwine.backend.module.order.infra.kafka;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.popwine.backend.core.exception.InternalServerException;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderEventPublisher {

    private final KafkaTemplate<String, String> kafkaTemplate;
    private final ObjectMapper objectMapper;

    /**
     * 주문 완료 이벤트를 Kafka에 발행
     *
     * @param event 주문 완료 이벤트
     */
    public void publish(OrderCompletedEvent event) {
        try {
            String json = objectMapper.writeValueAsString(event);
            kafkaTemplate.send("order-completed", json);
        } catch (JsonProcessingException e) {
            throw new InternalServerException("Order 이벤트 직렬화 실패");
        }
    }
}
