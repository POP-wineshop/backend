package com.popwine.backend.module.payment.controller;


import com.popwine.backend.core.response.ApiResponse;
import com.popwine.backend.module.payment.application.PaymentService;
import com.popwine.backend.module.payment.controller.dto.PaymentConfirmRequest;
import com.popwine.backend.module.payment.controller.dto.PaymentConfirmResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
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
     * @param orderId 주문 ID
     * @return 결제 승인 응답
     */

    @PostMapping("/{orderId}/confirm")
    public ApiResponse<PaymentConfirmResponse> confirmPayment(
            @RequestBody PaymentConfirmRequest request,
            @PathVariable("orderId") Long  orderId
    ) {
        PaymentConfirmResponse response = paymentService.confirmPayment(request, orderId);
        return ApiResponse.success(response);
    }
}