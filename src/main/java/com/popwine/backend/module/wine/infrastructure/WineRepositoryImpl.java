package com.popwine.backend.module.wine.infrastructure;



import com.popwine.backend.module.wine.domain.entity.QWine;
import com.popwine.backend.module.wine.domain.entity.QWineCategory;
import com.popwine.backend.module.wine.domain.repository.WineRepository;
import com.popwine.backend.module.wine.domain.entity.Wine;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

import static com.popwine.backend.module.wine.domain.entity.QCategory.category;

@Repository
@RequiredArgsConstructor
public class WineRepositoryImpl implements WineRepository {

    private final JpaWineRepository jpa;
    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public Optional<Wine> findById(Long id) {
        return jpa.findById(id);
    }

    @Override
    public List<Wine> findAll() {
        return jpa.findAll();
    }

    @Override
    public List<Wine> findByName(String name) {
        return jpa.findByName(name);
    }

    @Override
    public List<Wine> findByRegion(String region) {
        return jpa.findByRegion(region);
    }

    @Override
    public List<Wine> findByGrapeVariety(String grapeVariety) {
        return jpa.findByGrapeVariety(grapeVariety);
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
                .join(wine.wineCategories, wineCategory)
                .join(wineCategory.category, category)
                .where(category.id.in(categoryIds))
                .groupBy(wine.id)
                .having(category.id.countDistinct().eq((long) categoryIds.size()))
                .fetch();

    }

    @Override
    public Wine save(Wine wine) {
        return jpa.save(wine);
    }
}