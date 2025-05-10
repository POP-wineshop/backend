package com.popwine.backend.module.auth.entity;

import com.popwine.backend.core.BaseTimeEntity;
import com.popwine.backend.module.auth.vo.Password;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "users")
public class User extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String account;

    @Embedded
    @AttributeOverrides(
            @AttributeOverride(name = "password", column = @Column(name = "password", nullable = false))
    )
    private Password password;
}