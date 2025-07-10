package com.popwine.backend.module.wine.infra.repo.winelike;

import com.popwine.backend.module.wine.domain.entity.WineLike;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaWineLikeRepoImpl extends JpaRepository<WineLike, Long> {
}
