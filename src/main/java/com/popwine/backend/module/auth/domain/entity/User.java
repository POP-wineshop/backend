package com.popwine.backend.module.auth.domain.entity;

import com.popwine.backend.core.BaseTimeEntity;
import com.popwine.backend.module.auth.domain.vo.Password;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Table(name = "users")
public class User extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;

    private String name;
    @Embedded
    private Password password;
}