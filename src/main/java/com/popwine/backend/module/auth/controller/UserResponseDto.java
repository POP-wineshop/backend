package com.popwine.backend.module.auth.controller;

import com.popwine.backend.module.auth.domain.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;


@Getter
@AllArgsConstructor
public class UserResponseDto {
    private Long id;
    private String name;

    public static UserResponseDto from(User user) {
        return new UserResponseDto(user.getId(), user.getName());
    }
}