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

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DeliveryService {

    private final DeliveryRepository deliveryRepository;

    // 배송지 등록
    @Transactional
    public List<DeliveryResponseDto> createDeliveries(List<DeliveryRequestDto> requestDtos) {
        Long userId = SecurityUtil.getCurrentUserId();

        List<Delivery> savedList = new ArrayList<>();
        for (DeliveryRequestDto dto : requestDtos) {
            if (dto.isDefault()) {
                deliveryRepository.resetDefaultAddressForUser(userId);
            }

            Delivery saved = deliveryRepository.save(dto.toEntity());
            savedList.add(saved);
        }

        return savedList.stream()
                .map(DeliveryResponseDto::from)
                .toList();
    }


    //배송지 조회
    @Transactional(readOnly = true)
    public List<DeliveryResponseDto> getAllDeliveriesForUser() {
        Long userId = SecurityUtil.getCurrentUserId();
        List<Delivery> deliveries = deliveryRepository.findAllByUserId(userId);
        return deliveries.stream()
                .map(DeliveryResponseDto::from)
                .toList();
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
