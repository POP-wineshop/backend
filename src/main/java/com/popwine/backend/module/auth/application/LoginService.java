package com.popwine.backend.module.auth.application;

import com.popwine.backend.core.exception.BadRequestException;
import com.popwine.backend.core.exception.ErrorCode;
import com.popwine.backend.module.auth.controller.dto.LoginRequestDto;
import com.popwine.backend.module.auth.controller.dto.LoginResponseDto;
import com.popwine.backend.module.auth.domain.entity.User;
import com.popwine.backend.module.auth.domain.repository.UserRepository;
import com.popwine.backend.module.auth.domain.vo.Username;
import com.popwine.backend.module.auth.infrastructure.jwt.JwtTokenProvider;
import com.popwine.backend.module.auth.infrastructure.redis.RedisService;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoginService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;
    private final RedisService redisService;

    // 로그인 메서드
    public LoginResponseDto login(LoginRequestDto dto) {
        Username username = new Username(dto.getUsername());

        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new BadRequestException(ErrorCode.UNAUTHORIZED, "존재하지 않는 사용자입니다."));

        if (!user.getPassword().matches(dto.getPassword(), passwordEncoder)) {
            throw new BadRequestException(ErrorCode.UNAUTHORIZED, "비밀번호가 일치하지 않습니다.");
        }

        String accessToken = jwtTokenProvider.generateAccessToken(user.getId(), user.getUsername());
        String refreshToken = jwtTokenProvider.generateRefreshToken(user.getId(), user.getUsername());

        return new LoginResponseDto(accessToken, refreshToken);
    }



    public LoginResponseDto refreshToken(String refreshToken) {
        // 1. 토큰에서 username, userId 추출
        Claims claims = jwtTokenProvider.getClaims(refreshToken);
        Long userId = claims.get("userId", Long.class);
        String username = claims.getSubject();

        // 2. Redis에서 저장된 refreshToken과 비교
        String saved = redisService.getRefreshToken(userId);
        if (saved == null || !saved.equals(refreshToken)) {
            throw new BadRequestException(ErrorCode.UNAUTHORIZED, "유효하지 않은 Refresh Token");
        }

        // 3. 새 AccessToken 발급
        String newAccessToken = jwtTokenProvider.generateAccessToken(userId, username);

        return new LoginResponseDto(newAccessToken, refreshToken);
    }

}
