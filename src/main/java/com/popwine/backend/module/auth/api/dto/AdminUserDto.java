package com.popwine.backend.module.auth.api.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class AdminUserDto {
    private Long id;
    private String name;
}
