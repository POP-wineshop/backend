package com.popwine.backend.module.auth.api.dto;


import com.popwine.backend.core.exception.BadRequestException;
import com.popwine.backend.module.auth.domain.entity.User;

import com.popwine.backend.module.auth.domain.vo.Password;
import lombok.Getter;
import java.util.Objects;

@Getter
public class SignUpRequestDto {
    private final String name;
    private final String username;
    private final String password;
    private final String confirmPassword;

    public SignUpRequestDto(String name, String username, String password, String confirmPassword) {
        this.name = name;
        this.username = username;
        this.password = password;
        this.confirmPassword = confirmPassword;

        validatePasswordMatch();
    }

    private void validatePasswordMatch() {
        if (!Objects.equals(password, confirmPassword)) {
            throw new BadRequestException("비밀번호가 일치하지 않습니다.");
        }
    }

    public User toEntity(Password password) {
        return User.builder()
                .name(name)
                .username(username)
                .password(password)
                .build();
    }
}