package com.popwine.backend.module.auth.api.dto;

import com.popwine.backend.module.auth.domain.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;


@Getter
@AllArgsConstructor
public class SignUpResponseDto {
    private Long id;
    private String name;

    public static SignUpResponseDto from(User user) {
        return new SignUpResponseDto(user.getId(), user.getName());
    }
}