package com.popwine.backend.module.wine.domain.repository;

import com.popwine.backend.module.wine.domain.entity.Category;
import com.popwine.backend.module.wine.domain.enums.CategoryType;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoryRepository  {
    Optional<Category> findByNameAndType(String name, CategoryType type);
    Category save(Category category);

}
