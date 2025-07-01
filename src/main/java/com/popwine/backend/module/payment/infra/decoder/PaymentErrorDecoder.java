package com.popwine.backend.module.payment.infra.decoder;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import feign.Response;
import feign.codec.ErrorDecoder;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Slf4j
public class PaymentErrorDecoder implements ErrorDecoder {

    private final ErrorDecoder defaultDecoder = new Default();

    @Override
    public Exception decode(String methodKey, Response response) {
        try {
            String body = new String(response.body().asInputStream().readAllBytes(), StandardCharsets.UTF_8);

            // Toss 응답 바디 출력
             log.error("[Toss 응답 바디] {}", body);

            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode json = objectMapper.readTree(body);

            String code = json.has("code") ? json.get("code").asText() : "UNKNOWN_ERROR";
            String message = json.has("message") ? json.get("message").asText() : "Unknown error";

            return switch (code) {
                case "ALREADY_PROCESSED_PAYMENT" -> new IllegalStateException("이미 결제된 건입니다.");
                case "NOT_FOUND_PAYMENT_SESSION" -> new IllegalArgumentException("결제 세션이 만료되었습니다.");
                default -> new RuntimeException("결제 실패: " + message);
            };
        } catch (IOException e) {
            return defaultDecoder.decode(methodKey, response); // fallback
        }
    }
}