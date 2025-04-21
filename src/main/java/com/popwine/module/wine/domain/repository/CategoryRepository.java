package com.popwine.module.wine.domain.repository;

import com.popwine.module.wine.domain.Category;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoryRepository  {
    Optional<Category> findById(Long id);
    boolean existsById(Long id);
}
