package com.popwine.backend.module.wine.infra.kafka;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.popwine.backend.module.order.infra.kafka.OrderCompletedEvent;
import com.popwine.backend.module.wine.application.WineService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class WineStockConsumer {

    private final ObjectMapper objectMapper;
    private final WineService wineService;

    /**
     * 주문 완료 이벤트를 수신하여 와인 재고를 차감하는 Kafka 리스너
     *
     * @param record Kafka ConsumerRecord
     */
    @KafkaListener(topics = "order-completed", groupId = "wine-stock-group")
    public void consume(ConsumerRecord<String, String> record) {
        try {
            String message = record.value();
            OrderCompletedEvent event = objectMapper.readValue(message, OrderCompletedEvent.class);
            log.info("[Kafka] 주문 완료 이벤트 수신: orderId={}", event.getOrderId());

            // 와인 재고 차감 처리
            wineService.decreaseStockByOrder(event.getOrderId());

        } catch (Exception e) {
            log.error("[Kafka] 와인 재고 차감 실패", e);
        }
    }
}
