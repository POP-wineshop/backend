package com.popwine.backend.module.wine.infra;


import com.popwine.backend.module.wine.domain.entity.Category;
import com.popwine.backend.module.wine.domain.enums.CategoryType;
import com.popwine.backend.module.wine.domain.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

// infrastructure.CategoryRepositoryImpl
@RequiredArgsConstructor
@Repository
public class CategoryRepositoryImpl implements CategoryRepository {

    private final JpaCategoryRepository jpa;

    @Override
    public Optional<Category> findByNameAndType(String name, CategoryType type) {
        return jpa.findByNameAndType(name, type);
    }

    @Override
    public Category save(Category category) {
        return jpa.save(category);
    }
}
