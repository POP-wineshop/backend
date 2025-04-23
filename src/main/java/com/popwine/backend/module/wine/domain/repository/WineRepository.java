package com.popwine.backend.module.wine.domain.repository;

import com.popwine.backend.module.wine.domain.Wine;

import java.util.List;
import java.util.Optional;

public interface WineRepository {
     Optional<Wine> findById(Long id);
     List<Wine> findAll();
     List<Wine> findByName(String name);
     List<Wine> findByRegion(String region);
     List<Wine> findByGrapeVariety(String grapeVariety);
     List<Wine> findByDynamicFilters(String country, String region,String wineType);
}
