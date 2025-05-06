package com.popwine.backend.module.payment.infrastructure.config;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import lombok.RequiredArgsConstructor;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

@RequiredArgsConstructor
public class PaymentAuthInterceptor implements RequestInterceptor {
    private final PaymentProperties paymentProperties;

    @Override
    public void apply(RequestTemplate template) {
        String key = paymentProperties.getSecretKey() + ":";
        String encoded = Base64.getEncoder().encodeToString(key.getBytes(StandardCharsets.UTF_8));
        template.header("Authorization", "Basic " + encoded);
    }

}
