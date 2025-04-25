package com.popwine.backend.module.wine.infrastructure;



import com.popwine.backend.module.wine.domain.entity.QWine;
import com.popwine.backend.module.wine.domain.entity.QWineCategory;
import com.popwine.backend.module.wine.domain.repository.WineRepository;
import com.popwine.backend.module.wine.domain.entity.Wine;
import com.popwine.backend.module.wine.domain.enums.WineType;
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
    public List<Wine> findByCategoryFilters(List<Long> categoryIds) {
        QWine wine = QWine.wine;
        QWineCategory wineCategory = QWineCategory.wineCategory;

        if (categoryIds == null || categoryIds.isEmpty()) {
                return jpaQueryFactory.selectFrom(wine).fetch();
        }

        return jpaQueryFactory
                .selectFrom(wine)
                .join(wineCategory).on(wine.id.eq(wineCategory.wineId))
                .where(wineCategory.categoryId.in(categoryIds))
                .groupBy(wine.id)
                .having(wineCategory.categoryId.countDistinct().eq((long) categoryIds.size()))
                .fetch();
    }
}