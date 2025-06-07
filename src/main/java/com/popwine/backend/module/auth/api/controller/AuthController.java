package com.popwine.backend.module.auth.api.controller;


import com.popwine.backend.core.common.ApiResponse;
import com.popwine.backend.module.auth.api.controller.dto.*;
import com.popwine.backend.module.auth.api.dto.*;
import com.popwine.backend.module.auth.application.LoginService;
import com.popwine.backend.module.auth.application.SignUpService;
import com.popwine.backend.module.auth.controller.dto.*;
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

    @PostMapping("/refresh")
    public ApiResponse<LoginResponseDto> refresh(@RequestBody TokenRefreshRequest request) {
        return ApiResponse.success(loginService.refreshToken(request.getRefreshToken()));
    }

}
