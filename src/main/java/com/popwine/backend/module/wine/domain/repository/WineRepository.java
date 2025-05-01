package com.popwine.backend.module.wine.domain.repository;

import com.popwine.backend.module.wine.domain.entity.Wine;

import java.util.List;
import java.util.Optional;

public interface WineRepository {
     Optional<Wine> findById(Long id);
     List<Wine> findAll();
     List<Wine> findByCategoryAndNameFilters(List<Long> categoryIds, String keyword);

     Wine save(Wine wine);


}
