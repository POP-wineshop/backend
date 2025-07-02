package com.popwine.backend.module.wine.application;

import com.popwine.backend.core.exception.BadRequestException;
import com.popwine.backend.module.order.domain.entity.Order;
import com.popwine.backend.module.order.domain.repo.OrderRepository;
import com.popwine.backend.module.order.domain.vo.OrderItem;
import com.popwine.backend.module.wine.api.dto.WineRequestDto;
import com.popwine.backend.module.wine.domain.entity.Category;
import com.popwine.backend.module.wine.domain.enums.CategoryType;
import com.popwine.backend.module.wine.domain.repo.CategoryRepo;
import com.popwine.backend.module.wine.domain.repo.WineRepo;
import com.popwine.backend.module.wine.api.dto.WineResponseDto;
import com.popwine.backend.module.wine.domain.entity.Wine;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Service
@AllArgsConstructor
public class WineService {
    private final WineRepo wineRepo;
    private final CategoryRepo categoryRepo;
    private final OrderRepository orderRepository;


    //1. 모든 와인 조회
    @Transactional(readOnly = true)
    public List<WineResponseDto> getAllWines() {
        return wineRepo.findAll()
                .stream()
                .map(WineResponseDto::from)
                .collect(Collectors.toList());
    }

    //2. 카테고리 와인 조회
    @Transactional(readOnly = true)
    public List<WineResponseDto> searchWines(String country, String region, String type, String keyword) {
        // 필터가 전부 비어있다면 전체 와인 조회
        if (isEmpty(country) && isEmpty(region) && isEmpty(type) && isEmpty(keyword)) {
            return getAllWines();
        }

        // 아니면 필터 조건에 맞춰 검색
        List<Wine> wines = wineRepo.findByFilters(country, region, type, keyword);
        return wines.stream()
                .map(WineResponseDto::from)
                .toList();
    }

    // 문자열이 비었는지 확인하는 메서드
    private boolean isEmpty(String s) {
        return s == null || s.trim().isEmpty();
    }


    //3. 와인 ID통해 상세정보 조회
    public WineResponseDto getWineById(Long id) {
        return wineRepo.findById(id)
                .map(WineResponseDto::from)
                .orElseThrow(() -> new IllegalArgumentException("해당하는 와인을 찾을 수 없다: " + id));
    }

    @Transactional
    public List<WineResponseDto> createWine(List<WineRequestDto> dtos) {
        List<Wine> wines = new ArrayList<>();

        for (WineRequestDto dto : dtos) {
            Wine wine = dto.toEntity();

            Category country = getOrCreateCategory(dto.getCountry(), CategoryType.COUNTRY);
            Category region = getOrCreateCategory(dto.getRegion(), CategoryType.REGION);
            Category type = getOrCreateCategory(dto.getWineType().name(), CategoryType.WINE_TYPE);

            wine.addCategory(country);
            wine.addCategory(region);
            wine.addCategory(type);

            wines.add(wine);
        }

        List<Wine> saved = wineRepo.saveAll(wines);
        return saved.stream()
                .map(WineResponseDto::from)
                .toList();
    }




    //5.  카테고리 이름으로 카테고리 조회, 없으면 생성
    private Category getOrCreateCategory(String name, CategoryType type) {
        return categoryRepo.findByNameAndType(name, type)
                .orElseGet(() -> categoryRepo.save(new Category(name, type)));
    }


    //6. 와인 재고 감소 (주문 시 사용)
    @Transactional
    public void decreaseStockByOrder(Long orderId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new BadRequestException("주문 정보가 없습니다."));

        for (OrderItem item : order.getOrderItems()) {
            Wine wine = wineRepo.findById(item.getWineId())
                    .orElseThrow(() -> new BadRequestException("해당 와인이 존재하지 않습니다."));
            wine.decreaseStock(item.getOrderedQuantity().getQuantity());
        }
    }


}

