package com.popwine.backend.module.auth.api.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class LoginResponseDto {
    private final String accessToken;
    private final String refreshToken;
    private final String username;
}
