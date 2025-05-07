package com.popwine.backend.module.payment.controller;


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

    @PostMapping("/{orderId}/confirm")
    public ResponseEntity<PaymentConfirmResponse> confirmPayment(
            @RequestBody PaymentConfirmRequest request,
            @PathVariable("orderId") Long  orderId
    ) {
        PaymentConfirmResponse response = paymentService.confirmPayment(request, orderId);
        return ResponseEntity.ok(response);
    }
}