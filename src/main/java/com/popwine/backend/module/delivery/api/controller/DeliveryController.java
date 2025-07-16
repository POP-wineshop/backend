package com.popwine.backend.module.delivery.api.controller;

import com.popwine.backend.core.common.ApiResponse;
import com.popwine.backend.module.delivery.api.dto.DeliveryRequestDto;
import com.popwine.backend.module.delivery.api.dto.DeliveryResponseDto;
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
    public ApiResponse<List<DeliveryResponseDto>> createDelivery(@RequestBody List<DeliveryRequestDto> requestDto) {
        List<DeliveryResponseDto> response = deliveryService.createDeliveries(requestDto);
        return ApiResponse.success(response);
    }

    // 배송지 조회
    @GetMapping
    public ApiResponse<List<DeliveryResponseDto>> getAllDeliveries() {
        List<DeliveryResponseDto> response = deliveryService.getAllDeliveriesForUser();
        return ApiResponse.success(response);
    }

    // 기본 배송지 조회
    @GetMapping("/default")
    public ApiResponse<DeliveryResponseDto> getDefaultDelivery() {
        DeliveryResponseDto response = deliveryService.getDefaultDelivery();
        return ApiResponse.success(response);
    }


    // 배송지 수정
    @PutMapping("/{id}")
    public ApiResponse<DeliveryResponseDto> updateDelivery(@PathVariable Long id,@RequestBody DeliveryRequestDto requestDto) {
        DeliveryResponseDto response = deliveryService.updateDelivery(id, requestDto);
        return ApiResponse.success(response);
    }

    // 배송지 삭제 상태만 변경
    @DeleteMapping("/{id}")
    public ApiResponse<Void> deleteDelivery(@PathVariable Long id) {
        deliveryService.deleteDelivery(id);
        return ApiResponse.success();
    }

}
