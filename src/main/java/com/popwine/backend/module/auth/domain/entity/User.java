package com.popwine.backend.module.auth.domain.entity;

import com.popwine.backend.core.common.BaseTimeEntity;
import com.popwine.backend.module.auth.domain.vo.Password;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Table(name = "users")
public class User extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    @Embedded
    private Password password;
    private String name;

    public User(String username,Password password, String name) {
        this.username = username;
        this.password = password;
        this.name = name;
    }

    public static User of(String username,String name, Password password) {
        return new User(username, password, name);
    }

}