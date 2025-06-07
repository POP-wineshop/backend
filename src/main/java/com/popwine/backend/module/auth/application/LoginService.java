package com.popwine.backend.module.auth.application;

import com.popwine.backend.core.exception.BadRequestException;
import com.popwine.backend.core.exception.ErrorCode;
import com.popwine.backend.module.auth.api.dto.LoginRequestDto;
import com.popwine.backend.module.auth.api.dto.LoginResponseDto;
import com.popwine.backend.module.auth.api.dto.UserCacheDto;
import com.popwine.backend.module.auth.domain.entity.User;
import com.popwine.backend.module.auth.domain.repo.UserRepository;
import com.popwine.backend.module.auth.domain.vo.Username;
import com.popwine.backend.core.security.jwt.JwtTokenProvider;
import com.popwine.backend.core.infra.redis.RedisService;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
public class LoginService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;
    private final RedisService redisService;

    @Value("${jwt.refresh-token-validity}")
    private long refreshTokenValidity;

    // 로그인 메서드
    public LoginResponseDto login(LoginRequestDto dto) {

        Username username = new Username(dto.getUsername());

        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new BadRequestException(ErrorCode.UNAUTHORIZED, "존재하지 않는 사용자입니다."));

        if (!user.getPassword().matches(dto.getPassword(), passwordEncoder)) {
            throw new BadRequestException(ErrorCode.UNAUTHORIZED, "비밀번호가 일치하지 않습니다.");
        }

        // 토큰 발급
        String accessToken = jwtTokenProvider.generateAccessToken(user.getId(), user.getUsername());
        String refreshToken = jwtTokenProvider.generateRefreshToken(user.getId(), user.getUsername());

        // Refresh Token Redis 저장
        redisService.saveRefreshToken(user.getId(), refreshToken, refreshTokenValidity);

        // 유저 정보 Redis 캐싱 (TTL 예: 6시간)
        UserCacheDto userCacheDto = UserCacheDto.from(user); // of 메서드 또는 생성자 사용
        long userCacheTtl = TimeUnit.HOURS.toMillis(6);
        redisService.cacheUser(user.getId(), userCacheDto, userCacheTtl);
        return new LoginResponseDto(accessToken, refreshToken,user.getUsername());
    }

    // 리프레시 토큰을 통한 재발급
    public LoginResponseDto refreshToken(String refreshToken) {
        // 1. 토큰에서 정보 추출
        Claims claims = jwtTokenProvider.getClaims(refreshToken);
        Long userId = claims.get("userId", Long.class);
        String username = claims.getSubject();

        // 2. Redis 저장값과 비교
        String saved = redisService.getRefreshToken(userId);
        if (saved == null || !saved.equals(refreshToken)) {
            throw new BadRequestException(ErrorCode.UNAUTHORIZED, "유효하지 않은 Refresh Token");
        }

        // 3. Access Token 재발급
        String newAccessToken = jwtTokenProvider.generateAccessToken(userId, username);

        return new LoginResponseDto(newAccessToken, refreshToken, username);
    }
}
