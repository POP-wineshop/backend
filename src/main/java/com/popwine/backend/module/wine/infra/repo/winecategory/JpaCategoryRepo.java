package com.popwine.backend.module.wine.infra.repo.winecategory;

import com.popwine.backend.module.wine.domain.entity.Category;
import com.popwine.backend.module.wine.domain.enums.CategoryType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface JpaCategoryRepo extends JpaRepository<Category, Long> {
    Optional<Category> findByNameAndType(String name, CategoryType type);
}
