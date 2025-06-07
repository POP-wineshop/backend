package com.popwine.backend.module.auth.api.dto;


import com.popwine.backend.module.auth.domain.entity.User;

import com.popwine.backend.module.auth.domain.vo.Password;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SignUpRequestDto {

    private String name;
    private String username;
    private String password;


    public User toEntity(Password password) {
        return User.builder()
                .name(name)
                .username(username)
                .password(password)
                .build();
    }

}
