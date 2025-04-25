package com.popwine.backend.module.wine.domain.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "wine_category")
@Getter
@NoArgsConstructor
public class WineCategory {

    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private Long id;

    private Long wineId;
    private Long categoryId;

    public WineCategory(Long wineId, Long categoryId) {
        this.wineId = wineId;
        this.categoryId = categoryId;
    }
}
