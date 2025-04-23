package com.popwine.backend.module.wine.infrastructure;


import com.popwine.backend.module.wine.domain.QWine;
import com.popwine.backend.module.wine.domain.repository.WineRepository;
import com.popwine.backend.module.wine.domain.Wine;
import com.popwine.backend.module.wine.domain.vo.WineType;
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
    public List<Wine> findByDynamicFilters(String country, String region, String wineType) {
        BooleanBuilder builder = new BooleanBuilder();

        if (country != null && !country.isEmpty()) {
            builder.and(QWine.wine.origin.eq(country));
        }
        if (region != null && !region.isEmpty()) {
            builder.and(QWine.wine.region.eq(region));
        }
        if (wineType != null && !wineType.isEmpty()) {
            builder.and(QWine.wine.wineType.eq(WineType.from(wineType)));
        }
        return jpaQueryFactory.selectFrom(QWine.wine)
                .where(builder)
                .fetch();
    }
}