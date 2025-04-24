package com.popwine.backend.module.wine.infrastructure;

import com.popwine.backend.module.wine.domain.entity.Wine;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JpaWineRepository extends JpaRepository<Wine,Long> {
    List<Wine> findByName(String name);

    List<Wine> findByRegion(String region);

    List<Wine> findByGrapeVariety(String grapeVariety);
}
