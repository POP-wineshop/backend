package com.popwine.backend.module.auth.api.controller;

import com.popwine.backend.module.auth.api.dto.AdminUserDto;
import com.popwine.backend.module.auth.domain.repo.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/admin/users")
@RequiredArgsConstructor
public class AdminUserController {


    private final UserRepository userRepository;

    //전체 사용자 조회 API
    @GetMapping("/all")
    public List<AdminUserDto> getAllUsers() {
        return userRepository.findAll().stream()
                .map(user -> new AdminUserDto(user.getId(), user.getName()))
                .toList();
    }
}
