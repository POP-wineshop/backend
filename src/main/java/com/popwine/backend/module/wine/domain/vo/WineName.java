package com.popwine.backend.module.wine.domain.vo;

import com.popwine.backend.core.exception.BadRequestException;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Getter
@NoArgsConstructor
public class WineName {
    private String korean;
    private String english;

    public WineName(String korean, String english) {
        if (korean == null || korean.isEmpty()) {
            throw new BadRequestException("와인 이름(한글)은 null 또는 공백일 수 없다.");
        }
        if (english == null || english.isEmpty()) {
            throw new BadRequestException("와인 이름(영어)은 null 또는 공백일 수 없다.");
        }
        this.korean = korean;
        this.english = english;
    }





}
