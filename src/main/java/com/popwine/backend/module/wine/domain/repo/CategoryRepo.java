package com.popwine.backend.module.wine.domain.repo;

import com.popwine.backend.module.wine.domain.entity.Category;
import com.popwine.backend.module.wine.domain.enums.CategoryType;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoryRepo {
    Optional<Category> findByNameAndType(String name, CategoryType type);
    Category save(Category category);

}
