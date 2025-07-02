package com.popwine.backend.module.wine.infra.repo.wine;

import com.popwine.backend.module.wine.domain.entity.Wine;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JpaWineRepo extends JpaRepository<Wine,Long> {
    List<Wine> findByName(String name);

}
