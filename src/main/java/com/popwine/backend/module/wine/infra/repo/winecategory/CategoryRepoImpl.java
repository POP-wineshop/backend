package com.popwine.backend.module.wine.infra.repo.winecategory;


import com.popwine.backend.module.wine.domain.entity.Category;
import com.popwine.backend.module.wine.domain.enums.CategoryType;
import com.popwine.backend.module.wine.domain.repo.CategoryRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

// infrastructure.CategoryRepositoryImpl
@RequiredArgsConstructor
@Repository
public class CategoryRepoImpl implements CategoryRepo {

    private final JpaCategoryRepo jpa;

    @Override
    public Optional<Category> findByNameAndType(String name, CategoryType type) {
        return jpa.findByNameAndType(name, type);
    }

    @Override
    public Category save(Category category) {
        return jpa.save(category);
    }
}
