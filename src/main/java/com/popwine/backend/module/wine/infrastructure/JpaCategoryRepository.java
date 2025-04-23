package com.popwine.backend.module.wine.infrastructure;

import com.popwine.backend.module.wine.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaCategoryRepository extends JpaRepository<Category, Long> {
}
