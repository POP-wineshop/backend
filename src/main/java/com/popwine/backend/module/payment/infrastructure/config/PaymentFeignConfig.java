package com.popwine.backend.module.payment.infrastructure.config;

import com.popwine.backend.module.payment.infrastructure.decoder.PaymentErrorDecoder;
import feign.Request;
import feign.RequestInterceptor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.util.concurrent.TimeUnit;

@Configuration
@RequiredArgsConstructor
public class PaymentFeignConfig {

    private final PaymentProperties paymentProperties;

    @Bean
    public RequestInterceptor paymentAuthInterceptor() {
        return new PaymentAuthInterceptor(paymentProperties);
    }

    @Bean
    public
    Request.Options requestOptions() {
        return new Request.Options(2, TimeUnit.SECONDS, 60, TimeUnit.SECONDS, true);
    }

    @Bean
    public PaymentErrorDecoder paymentErrorDecoder() {
        return new PaymentErrorDecoder();
    }
}
