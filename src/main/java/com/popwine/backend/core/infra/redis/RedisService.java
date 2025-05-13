package com.popwine.backend.core.infra.redis;

import com.popwine.backend.module.auth.controller.dto.UserCacheDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor

public class RedisService {

    private final RedisTemplate<String, Object> redisTemplate;

    private static final String REFRESH_TOKEN_PREFIX = "refreshToken:";
    private static final String USER_PREFIX = "user:";

    // 1. RefreshToken 저장 (TTL = 만료 기간과 동일)
    public void saveRefreshToken(Long userId, String token, long ttlMillis) {
        String key = REFRESH_TOKEN_PREFIX + userId;
        redisTemplate.opsForValue().set(key, token, ttlMillis, TimeUnit.MILLISECONDS);
    }

    // 2. RefreshToken 조회
    public String getRefreshToken(Long userId) {
        return (String) redisTemplate.opsForValue().get(REFRESH_TOKEN_PREFIX + userId);
    }

    // 3. RefreshToken 삭제 (로그아웃 등)
    public void deleteRefreshToken(Long userId) {
        redisTemplate.delete(REFRESH_TOKEN_PREFIX + userId);
    }

    //4.  유저 정보 캐싱 (ex. 로그인 시, TTL 6시간)
    public void cacheUser(Long userId, UserCacheDto user, long ttlMillis) {
        redisTemplate.opsForValue().set(USER_PREFIX + userId, user, ttlMillis, TimeUnit.MILLISECONDS);
    }

    //5.  캐시된 유저 정보 조회
    public UserCacheDto getCachedUser(Long userId) {
        return (UserCacheDto) redisTemplate.opsForValue().get(USER_PREFIX + userId);
    }
}
