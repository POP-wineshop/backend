package com.popwine.module.wine.domain;

import com.popwine.module.wine.domain.vo.Price;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Wine {
    @Id
    private Long id;

    private String name;


    @Embedded
    private Price price;

    private int vintage;

    private String origin;
}
