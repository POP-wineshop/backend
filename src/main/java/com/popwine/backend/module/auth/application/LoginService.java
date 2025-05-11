package com.popwine.backend.module.auth.application;

import com.popwine.backend.core.exception.BadRequestException;
import com.popwine.backend.core.exception.ErrorCode;
import com.popwine.backend.module.auth.controller.dto.LoginRequestDto;
import com.popwine.backend.module.auth.controller.dto.LoginResponseDto;
import com.popwine.backend.module.auth.domain.entity.User;
import com.popwine.backend.module.auth.domain.repository.UserRepository;
import com.popwine.backend.module.auth.domain.vo.Username;
import com.popwine.backend.module.auth.infrastructure.jwt.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoginService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;

    // 로그인 메서드
    public LoginResponseDto login(LoginRequestDto dto) {
        Username username = new Username(dto.getUsername());

        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new BadRequestException(ErrorCode.UNAUTHORIZED, "존재하지 않는 사용자입니다."));

        if (!user.getPassword().matches(dto.getPassword(), passwordEncoder)) {
            throw new BadRequestException(ErrorCode.UNAUTHORIZED, "비밀번호가 일치하지 않습니다.");
        }

        String accessToken = jwtTokenProvider.generateAccessToken(user.getUsername());
        String refreshToken = jwtTokenProvider.generateRefreshToken(user.getUsername());

        return new LoginResponseDto(accessToken, refreshToken);
    }


}
