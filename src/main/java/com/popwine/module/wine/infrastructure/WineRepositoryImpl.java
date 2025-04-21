package com.popwine.module.wine.infrastructure;


import com.popwine.module.wine.domain.Wine;
import com.popwine.module.wine.domain.repository.WineRepository;
import com.popwine.module.wine.domain.vo.Price;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class WineRepositoryImpl implements WineRepository {

    private final JpaWineRepository jpaWineRepository;

    @Override
    public Optional<Wine> findById(Long id) {
        return jpaWineRepository.findById(id);
    }

    @Override
    public List<Wine> findAll() {
        return jpaWineRepository.findAll();
    }

    @Override
    public List<Wine> findByName(String name) {
        return jpaWineRepository.findByName(name);
    }

    @Override
    public List<Wine> findByRegion(String region) {
        return jpaWineRepository.findByRegion(region);
    }

    @Override
    public List<Wine> findByGrapeVariety(String grapeVariety) {
        return jpaWineRepository.findByGrapeVariety(grapeVariety);
    }

    @Override
    public List<Wine> findByPriceRange(Price minPrice, Price maxPrice) {
        // Price 필드가 Embedded 객체이므로, JPA 쿼리에서 직접 사용할 수 없습니다.
        // 가격 범위를 기준으로 검색하는 쿼리를 작성
        // return jpaWineRepository.findByPriceBetween(minPrice, maxPrice);
        return List.of(); // 임시로 빈 리스트 반환
    }
}