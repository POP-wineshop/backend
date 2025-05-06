package com.popwine.backend.module.payment.infrastructure.client;


import com.popwine.backend.module.payment.controller.dto.PaymentConfirmRequest;
import com.popwine.backend.module.payment.controller.dto.PaymentConfirmResponse;
import com.popwine.backend.module.payment.infrastructure.config.PaymentFeignConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(
        name = "tossPaymentClient",
        url = "https://api.tosspayments.com/v1",
        configuration = PaymentFeignConfig.class
)
public interface TossPaymentClient {
    @PostMapping("/payments/confirm")
    PaymentConfirmResponse confirmPayment(@RequestBody PaymentConfirmRequest request);
}
