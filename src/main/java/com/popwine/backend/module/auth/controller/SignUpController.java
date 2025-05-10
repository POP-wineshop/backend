package com.popwine.backend.module.auth.controller;


import com.popwine.backend.core.response.ApiResponse;
import com.popwine.backend.module.auth.application.SignUpService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class SignUpController {

    private final SignUpService signupService;

    // 회원가입 API
    @PostMapping("/signup")
    public ApiResponse<UserResponseDto> signUp(@RequestBody UserRequestDto dto) {
        UserResponseDto userResponseDto = signupService.register(dto);
        return ApiResponse.success(userResponseDto);
    }
}
