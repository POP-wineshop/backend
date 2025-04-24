package com.popwine.backend.module.wine.domain.repository;

import com.popwine.backend.module.wine.domain.entity.Category;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoryRepository  {
    Optional<Category> findById(Long id);
    boolean existsById(Long id);
}
