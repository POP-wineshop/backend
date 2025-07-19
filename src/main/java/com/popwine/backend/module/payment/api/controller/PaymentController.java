package com.popwine.backend.module.payment.api.controller;


import com.popwine.backend.core.common.ApiResponse;
import com.popwine.backend.module.payment.application.PaymentService;
import com.popwine.backend.module.payment.api.dto.PaymentConfirmReq;
import com.popwine.backend.module.payment.api.dto.PaymentConfirmRes;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;



@RestController
@RequiredArgsConstructor
@RequestMapping("/api/payments")
public class PaymentController {

    private final PaymentService paymentService;

    /**
     * 결제 승인 요청
     *
     * @param request 결제 승인 요청 정보
     * @return 결제 승인 응답
     */

    @PostMapping("/confirm")
    public ApiResponse<PaymentConfirmRes> confirmPayment(
            @RequestBody PaymentConfirmReq request
            ) {

        PaymentConfirmRes response = paymentService.confirmPayment(request);
        return ApiResponse.success(response);
    }
}