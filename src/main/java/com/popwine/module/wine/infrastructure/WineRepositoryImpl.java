package com.popwine.module.wine.infrastructure;


import com.popwine.module.wine.domain.QWine;
import com.popwine.module.wine.domain.Wine;
import com.popwine.module.wine.domain.repository.WineRepository;
import com.popwine.module.wine.domain.vo.Price;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class WineRepositoryImpl implements WineRepository {

    private final JpaWineRepository jpaWineRepository;
    private final JPAQueryFactory jpaQueryFactory;

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

    @Override
    public List<Wine> findByCategoryIdsContainingAny(List<Long> categoryIds) {
        return List.of();
    }

    @Override
    public List<Wine> findByDynamicFilters(String country, String region, String winery, String wineType) {
        BooleanBuilder builder = new BooleanBuilder();

        if (country != null && !country.isEmpty()) {
            builder.and(QWine.wine.origin.eq(country));
        }
        if (region != null && !region.isEmpty()) {
            builder.and(QWine.wine.region.eq(region));
        }
        if (winery != null && !winery.isEmpty()) {
            builder.and(QWine.wine.winery.eq(winery));
        }
        if (wineType != null && !wineType.isEmpty()) {
            builder.and(QWine.wine.body.eq(wineType));
        }

        return jpaQueryFactory.selectFrom(QWine.wine)
                .where(builder)
                .fetch();
    }
}