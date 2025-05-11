package com.popwine.backend.module.auth.controller;


import com.popwine.backend.core.response.ApiResponse;
import com.popwine.backend.module.auth.application.LoginService;
import com.popwine.backend.module.auth.application.SignUpService;
import com.popwine.backend.module.auth.controller.dto.LoginRequestDto;
import com.popwine.backend.module.auth.controller.dto.LoginResponseDto;
import com.popwine.backend.module.auth.controller.dto.SignUpRequestDto;
import com.popwine.backend.module.auth.controller.dto.SignUpResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {

    private final SignUpService signupService;
    private final LoginService loginService;

    // 회원가입 API
    @PostMapping("/signup")
    public ApiResponse<SignUpResponseDto> signUp(@RequestBody SignUpRequestDto dto) {
        return ApiResponse.success(signupService.signup(dto));
    }

    // 로그인
    @PostMapping("/login")
    public ApiResponse<LoginResponseDto> login(@RequestBody LoginRequestDto dto) {
        return ApiResponse.success(loginService.login(dto));
    }
}
