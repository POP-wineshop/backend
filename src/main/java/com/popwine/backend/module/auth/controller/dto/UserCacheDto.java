package com.popwine.backend.module.auth.controller.dto;

import com.popwine.backend.module.auth.domain.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserCacheDto implements Serializable {

    private Long userId;
    private String username;

    public static UserCacheDto from(User user) {
        return UserCacheDto.builder()
                .userId(user.getId())
                .username(user.getUsername())
                .build();
    }
}
