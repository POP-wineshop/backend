package com.popwine.backend.module.wine.infra;



import com.popwine.backend.module.wine.domain.entity.QCategory;
import com.popwine.backend.module.wine.domain.entity.QWine;
import com.popwine.backend.module.wine.domain.entity.QWineCategory;
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
    public List<Wine> findByCategoryAndNameFilters(List<Long> categoryIds, String keyword) {
        QWine wine = QWine.wine;
        QWineCategory wineCategory = QWineCategory.wineCategory;
        QCategory category = QCategory.category;

        BooleanBuilder condition = new BooleanBuilder();

        // 이름 검색 조건 (한글 또는 영어)
        if (keyword != null && !keyword.isBlank()) {
            condition.and(
                    wine.name.korean.containsIgnoreCase(keyword)
                            .or(wine.name.english.containsIgnoreCase(keyword))
            );
        }

        // 카테고리 필터가 있을 경우
        if (categoryIds != null && !categoryIds.isEmpty()) {
            condition.and(category.id.in(categoryIds));

            return jpaQueryFactory
                    .selectFrom(wine)
                    .join(wine.wineCategories, wineCategory)
                    .join(wineCategory.category, category)
                    .where(condition)
                    .groupBy(wine.id)
                    .having(category.id.countDistinct().eq((long) categoryIds.size()))
                    .fetch();
        }

        // 카테고리 필터 없을 경우 (이름 검색만)
        return jpaQueryFactory
                .selectFrom(wine)
                .where(condition)
                .fetch();
    }

    @Override
    public Wine save(Wine wine) {
        return jpa.save(wine);
    }
}