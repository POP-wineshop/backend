package com.popwine.backend.module.wine.domain;

import com.popwine.backend.module.wine.domain.vo.CategoryType;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "category")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String name;

    @Enumerated(EnumType.STRING)
    private CategoryType type;

    public Category(String name, CategoryType type) {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("카테고리 이름은 null 또는 공백일 수 없다.");
        }
        if (type == null || type.getValue().isEmpty()) {
            throw new IllegalArgumentException("카테고리 타입은 null 또는 공백일 수 없다.");
        }
        this.name = name;
        this.type = type;
    }}
