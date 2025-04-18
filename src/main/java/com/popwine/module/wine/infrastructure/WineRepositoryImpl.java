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
        // Price 필드가 Embedded 객체이므로, 이 메서드는 별도의 쿼리 메서드나 JPQL을 사용해야 합니다.
        return List.of(); // 구현 필요
    }
}