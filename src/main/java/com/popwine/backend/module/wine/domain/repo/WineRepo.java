package com.popwine.backend.module.wine.domain.repo;

import com.popwine.backend.module.wine.domain.entity.Wine;

import java.util.List;
import java.util.Optional;

public interface WineRepo {
     Optional<Wine> findById(Long id);
     List<Wine> findAll();
     List<Wine> findByFilters(String country, String region, String type, String keyword);


     Wine save(Wine wine);


     List<Wine> findAllById(List<Long> wineIds);

     List<Wine> saveAll(List<Wine> wines);
}
