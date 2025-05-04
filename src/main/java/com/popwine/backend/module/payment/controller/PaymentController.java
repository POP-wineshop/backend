package com.popwine.backend.module.payment.controller;


import com.popwine.backend.module.payment.controller.dto.PaymentConfirmRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.json.simple.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

@RestController
@RequestMapping("/api/payment")
public class PaymentController {

    @PostMapping("/confirm")
    public ResponseEntity<?> confirmPayment(@RequestBody PaymentConfirmRequest request) {
        String secretKey = "test_gsk_docs_OaPz8L5KdmQXkzRz3y47BMw6"; //테스트키 환경변수 처리 X
        String encodedAuth = "Basic " + Base64.getEncoder().encodeToString((secretKey + ":").getBytes());

        JSONObject payload = new JSONObject();
        payload.put("paymentKey", request.getPaymentKey());
        payload.put("orderId", request.getOrderId());
        payload.put("amount", request.getAmount());

        try {
            URL url = new URL("https://api.tosspayments.com/v1/payments/confirm");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Authorization", encodedAuth);
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setDoOutput(true);

            OutputStream os = conn.getOutputStream();
            os.write(payload.toJSONString().getBytes(StandardCharsets.UTF_8));
            os.flush();

            int responseCode = conn.getResponseCode();
            InputStream is = (responseCode == 200) ? conn.getInputStream() : conn.getErrorStream();
            String result = new BufferedReader(new InputStreamReader(is)).lines().reduce("", String::concat);

            return ResponseEntity.status(responseCode).body(result);

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("토스 결제 승인 실패: " + e.getMessage());
        }
    }
}
