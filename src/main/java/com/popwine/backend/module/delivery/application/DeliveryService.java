package com.popwine.backend.module.delivery.application;

import com.popwine.backend.core.exception.BadRequestException;
import com.popwine.backend.core.security.util.SecurityUtil;
import com.popwine.backend.module.delivery.api.dto.DeliveryRequestDto;
import com.popwine.backend.module.delivery.api.dto.DeliveryResponseDto;
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

        Long userId = SecurityUtil.getCurrentUserId();

        // 기본 배송지 설정 시, 기존 기본 배송지의 상태를 변경
        if (requestDto.isDefault()) {
            deliveryRepository.resetDefaultAddressForUser(userId);
        }

        Delivery delivery = requestDto.toEntity();
        Delivery savedDelivery = deliveryRepository.save(delivery);
        return DeliveryResponseDto.from(savedDelivery);
    }

    // 배송지 조회
    @Transactional(readOnly = true)
    public DeliveryResponseDto getDelivery(Long id) {
        Delivery delivery = deliveryRepository.findById(id);
        return DeliveryResponseDto.from(delivery);
    }

    // 기본 배송지 조회
    @Transactional(readOnly = true)
    public DeliveryResponseDto getDefaultDelivery() {
        Long userId = SecurityUtil.getCurrentUserId();
        Delivery delivery = deliveryRepository.findDefaultDeliveryByUserId(userId);

        if (delivery == null) {
            throw new BadRequestException("기본 배송지가 없습니다.");
        }

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
