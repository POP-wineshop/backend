package com.popwine.backend.module.wine.infra.repo;



import com.popwine.backend.module.wine.domain.entity.QCategory;
import com.popwine.backend.module.wine.domain.entity.QWine;
import com.popwine.backend.module.wine.domain.entity.QWineCategory;
import com.popwine.backend.module.wine.domain.enums.CategoryType;
import com.popwine.backend.module.wine.domain.repository.WineRepository;
import com.popwine.backend.module.wine.domain.entity.Wine;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
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

    //  와인 필터링 기능 구현
    @Override
    public List<Wine> findByFilters(String country, String region, String type, String keyword) {
        QWine wine = QWine.wine;
        QWineCategory wineCategory = QWineCategory.wineCategory;
        QCategory category = QCategory.category;

        BooleanBuilder condition = new BooleanBuilder(); // 검색어 (keyword)
        if (keyword != null && !keyword.isBlank()) {
            condition.andAnyOf(
                    wine.name.korean.containsIgnoreCase(keyword),
                    wine.name.english.containsIgnoreCase(keyword),
                    wine.wineType.stringValue().containsIgnoreCase(keyword),
                    category.name.containsIgnoreCase(keyword)
            );
        }

        //  조건을 리스트로 만들기
        List<CategoryFilter> filters = new ArrayList<>();
        if (type != null && !type.isBlank()) {
            filters.add(new CategoryFilter(CategoryType.WINE_TYPE, type));
        }
        if (country != null && !country.isBlank()) {
            filters.add(new CategoryFilter(CategoryType.COUNTRY, country));
        }
        if (region != null && !region.isBlank()) {
            filters.add(new CategoryFilter(CategoryType.REGION, region));
        }

        // 조건들을 or로 묶기
        BooleanExpression categoryCond = null;
        for (CategoryFilter filter : filters) {
            BooleanExpression oneCond = category.name.eq(filter.name)
                    .and(category.type.eq(filter.type));
            if (categoryCond == null) {
                categoryCond = oneCond;
            } else {
                categoryCond = categoryCond.or(oneCond);
            }
        }

        // null 처리 (조건이 없을 때를 대비)
        if (categoryCond == null) {
            categoryCond = Expressions.TRUE.isTrue(); // 무조건 참
        }

        // 쿼리 실행
        return jpaQueryFactory
                .selectFrom(wine)
                .distinct()
                .join(wine.wineCategories, wineCategory)
                .join(wineCategory.category, category)
                .where(condition.and(categoryCond))
                .groupBy(wine.id)
                .having(category.type.countDistinct().eq((long) filters.size()))
                .fetch();
    }

    private static class CategoryFilter {
        CategoryType type;
        String name;

        public CategoryFilter(CategoryType type, String name) {
            this.type = type;
            this.name = name;
        }
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