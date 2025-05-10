package com.popwine.backend.module.auth.domain.vo;


import com.popwine.backend.core.exception.BadRequestException;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Getter
@NoArgsConstructor
public class Username {

    private String value;

    public Username(String value) {
        if (value == null || value.isEmpty()) {
            throw new BadRequestException("아이디는 공백일 수 없고 필수입니다.");
        }
        this.value = value;
    }
}
