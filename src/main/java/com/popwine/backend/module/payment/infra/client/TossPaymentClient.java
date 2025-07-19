package com.popwine.backend.module.payment.infra.client;


import com.popwine.backend.module.payment.api.dto.PaymentConfirmReq;
import com.popwine.backend.module.payment.api.dto.PaymentConfirmRes;
import com.popwine.backend.module.payment.infra.config.PaymentFeignConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

//결제 승인 API 연동
@FeignClient(
        name = "tossPaymentClient",
        url = "https://api.tosspayments.com/v1",
        configuration = PaymentFeignConfig.class
)
public interface TossPaymentClient {
    @PostMapping("/payments/confirm")
    PaymentConfirmRes confirmPayment(@RequestBody PaymentConfirmReq request);
}
