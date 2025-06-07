package com.popwine.backend.module.delivery.api.controller;

import com.popwine.backend.core.common.ApiResponse;
import com.popwine.backend.module.delivery.api.dto.DeliveryRequestDto;
import com.popwine.backend.module.delivery.api.dto.DeliveryResponseDto;
import com.popwine.backend.module.delivery.application.DeliveryService;
import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/delivery")
@RequiredArgsConstructor
public class DeliveryController {

    private final DeliveryService deliveryService;

    // 배송지 등록
    @PostMapping("/create")
    public ApiResponse<DeliveryResponseDto> createDelivery(@RequestBody DeliveryRequestDto requestDto) {
        DeliveryResponseDto response = deliveryService.createDelivery(requestDto);
        return ApiResponse.success(response);
    }

    // 배송지 조회
    @GetMapping("/{id}")
    public ApiResponse<DeliveryResponseDto> getDelivery(@PathVariable Long id) {
        DeliveryResponseDto response = deliveryService.getDelivery(id);
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
