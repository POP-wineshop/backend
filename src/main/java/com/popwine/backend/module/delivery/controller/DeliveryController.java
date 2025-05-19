package com.popwine.backend.module.delivery.controller;

import com.popwine.backend.core.common.ApiResponse;
import com.popwine.backend.module.delivery.application.DeliveryService;
import com.popwine.backend.module.delivery.controller.dto.DeliveryRequestDto;
import com.popwine.backend.module.delivery.controller.dto.DeliveryResponseDto;
import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/delivery")
@RequiredArgsConstructor
public class DeliveryController {

    private final DeliveryService deliveryService;
    // 배송지 등록

    public ApiResponse<DeliveryResponseDto> createDelivery(DeliveryRequestDto requestDto) {
        DeliveryResponseDto response = deliveryService.createDelivery(requestDto);
        return ApiResponse.success(response);
    }

    // 배송지 조회
    public ApiResponse<DeliveryResponseDto> getDelivery(Long id) {
        DeliveryResponseDto response = deliveryService.getDelivery(id);
        return ApiResponse.success(response);
    }
    // 배송지 수정
    // 배송지 삭제 상태만 변경
    // 기본 배송지 조회
}
