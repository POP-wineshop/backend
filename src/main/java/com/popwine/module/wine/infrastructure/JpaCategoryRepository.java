package com.popwine.module.wine.infrastructure;

import com.popwine.module.wine.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaCategoryRepository extends JpaRepository<Category, Long> {
}
