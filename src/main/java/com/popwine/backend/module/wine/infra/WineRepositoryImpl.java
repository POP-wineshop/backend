package com.popwine.backend.module.wine.infra;



import com.popwine.backend.module.wine.domain.entity.QCategory;
import com.popwine.backend.module.wine.domain.entity.QWine;
import com.popwine.backend.module.wine.domain.entity.QWineCategory;
import com.popwine.backend.module.wine.domain.enums.CategoryType;
import com.popwine.backend.module.wine.domain.repository.WineRepository;
import com.popwine.backend.module.wine.domain.entity.Wine;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


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
    public List<Wine> findByFilters(String country, String region, String type, String keyword) {
        QWine wine = QWine.wine;
        QWineCategory wineCategory = QWineCategory.wineCategory;
        QCategory category = QCategory.category;

        BooleanBuilder condition = new BooleanBuilder();
        BooleanBuilder categoryCondition = new BooleanBuilder();

        if (keyword != null && !keyword.isBlank()) {
            condition.andAnyOf(
                    wine.name.korean.containsIgnoreCase(keyword),
                    wine.name.english.containsIgnoreCase(keyword),
                    wine.wineType.stringValue().containsIgnoreCase(keyword),
                    category.name.containsIgnoreCase(keyword)
            );
        }

        if (type != null && !type.isBlank()) {
            categoryCondition.or(
                    category.name.eq(type)
                            .and(category.type.eq(CategoryType.WINE_TYPE))
            );
        }

        if (country != null && !country.isBlank()) {
            categoryCondition.or(
                    category.name.eq(country)
                            .and(category.type.eq(CategoryType.COUNTRY))
            );
        }

        if (region != null && !region.isBlank()) {
            categoryCondition.or(
                    category.name.eq(region)
                            .and(category.type.eq(CategoryType.REGION))
            );
        }

        return jpaQueryFactory
                .selectFrom(wine)
                .distinct()
                .join(wine.wineCategories, wineCategory)
                .join(wineCategory.category, category)
                .where(condition.and(categoryCondition))
                .fetch();
    }


    @Override
    public Wine save(Wine wine) {
        return jpa.save(wine);
    }

    @Override
    public List<Wine> findAllById(List<Long> wineIds) {
        return jpa.findAllById(wineIds);
    }

}