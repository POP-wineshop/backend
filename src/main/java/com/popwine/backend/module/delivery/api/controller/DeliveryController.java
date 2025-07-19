package com.popwine.backend.module.delivery.api.controller;

import com.popwine.backend.core.common.ApiResponse;
import com.popwine.backend.module.delivery.api.dto.DeliveryReq;
import com.popwine.backend.module.delivery.api.dto.DeliveryRes;
import com.popwine.backend.module.delivery.application.DeliveryService;
import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/delivery")
@RequiredArgsConstructor
public class DeliveryController {

    private final DeliveryService deliveryService;

    // 배송지 등록
    @PostMapping("/create")
    public ApiResponse<List<DeliveryRes>> createDelivery(@RequestBody List<DeliveryReq> requestDto) {
        List<DeliveryRes> response = deliveryService.createDeliveries(requestDto);
        return ApiResponse.success(response);
    }

    // 배송지 조회
    @GetMapping
    public ApiResponse<List<DeliveryRes>> getAllDeliveries() {
        List<DeliveryRes> response = deliveryService.getAllDeliveriesForUser();
        return ApiResponse.success(response);
    }

    // 기본 배송지 조회
    @GetMapping("/default")
    public ApiResponse<DeliveryRes> getDefaultDelivery() {
        DeliveryRes response = deliveryService.getDefaultDelivery();
        return ApiResponse.success(response);
    }


    // 배송지 수정
    @PutMapping("/{id}")
    public ApiResponse<DeliveryRes> updateDelivery(@PathVariable Long id, @RequestBody DeliveryReq requestDto) {
        DeliveryRes response = deliveryService.updateDelivery(id, requestDto);
        return ApiResponse.success(response);
    }

    // 배송지 삭제 상태만 변경
    @DeleteMapping("/{id}")
    public ApiResponse<Void> deleteDelivery(@PathVariable Long id) {
        deliveryService.deleteDelivery(id);
        return ApiResponse.success();
    }

}
