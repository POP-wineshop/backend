package com.popwine.backend.module.delivery.application;

import com.popwine.backend.module.delivery.controller.dto.DeliveryRequestDto;
import com.popwine.backend.module.delivery.controller.dto.DeliveryResponseDto;
import com.popwine.backend.module.delivery.domain.entity.Delivery;
import com.popwine.backend.module.delivery.domain.repository.DeliveryRepository;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DeliveryService {

    private final DeliveryRepository deliveryRepository;

    // 배송지 등록
    @Transactional
    public DeliveryResponseDto createDelivery(DeliveryRequestDto requestDto) {
        Delivery delivery = requestDto.toEntity(); // DeliveryRequestDto -> Delivery 변환
        Delivery savedDelivery = deliveryRepository.save(delivery); // 저장된 Delivery 반환
        return DeliveryResponseDto.from(savedDelivery); // Delivery -> DeliveryResponseDto 변환 후 반환
    }

    // 배송지 조회
    @Transactional(readOnly = true)
    public DeliveryResponseDto getDelivery(Long id) {
        Delivery delivery = deliveryRepository.findById(id);
        return DeliveryResponseDto.from(delivery);
    }

    // 배송지 수정
    @Transactional
    public DeliveryResponseDto updateDelivery(Long id, DeliveryRequestDto requestDto) {
        Delivery delivery = deliveryRepository.findById(id);
        delivery.update(requestDto.toEntity());
        return DeliveryResponseDto.from(delivery);
    }

    // 배송지 삭제 상태만 변경
    @Transactional
    public void deleteDelivery(Long id) {
        Delivery delivery = deliveryRepository.findById(id);
    }

}
