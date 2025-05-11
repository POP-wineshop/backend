package com.popwine.backend.module.auth.infrastructure.jwt;

import com.popwine.backend.core.exception.BadRequestException;
import com.popwine.backend.core.exception.ErrorCode;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;


import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;
import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class JwtTokenProvider {

    @Value("${jwt.secret}")
    private String secretKey;

    @Value("${jwt.access-token-validity}")
    private long accessTokenValidity;

    @Value("${jwt.refresh-token-validity}")
    private long refreshTokenValidity;

    private Key key;

    /**
     * JWT 토큰 생성 시 사용할 비밀키를 Base64로 인코딩하여 설정
     * @PostConstruct 어노테이션을 사용하여 초기화 메서드로 설정
     */
    @PostConstruct
    protected void init() {
        this.key = Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8));
    }

    /**
     * JWT Access Token을 생성
     * @param username 사용자 이름
     * @return 생성된 Access Token
     */
    public String generateAccessToken(String username) {
        return Jwts.builder()
                .setSubject(username)
                .setExpiration(new Date(System.currentTimeMillis() + accessTokenValidity))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    /**
     * JWT Refresh Token을 생성
     * @param username 사용자 이름
     * @return 생성된 Refresh Token
     */
    public String generateRefreshToken(String username) {
        return Jwts.builder()
                .setSubject(username)
                .setExpiration(new Date(System.currentTimeMillis() + refreshTokenValidity))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    /**
     * JWT Access Token에서 사용자 이름을 추출.
     * @param token JWT Access Token
     * @return 사용자 이름
     */
    public Authentication getAuthentication(String token) {
        String username = getUsernameFromToken(token);
        UserDetails userDetails = new User(username, "", List.of());
        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }

    /**
     * JWT 토큰에서 사용자 이름을 추출한 뒤,
     * Spring Security의 Authentication 객체로 변환
     * SecurityContextHolder에 인증 정보를 설정
     *
     * @param token JWT Access Token
     * @return 인증 객체 (UsernamePasswordAuthenticationToken)
     */
    public String getUsernameFromToken(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    /**
     * JWT Access Token의 유효성을 검증합니다.
     * @param token JWT Access Token
     */
    public void validateTokenOrThrow(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
        } catch (ExpiredJwtException e) {
            throw new BadRequestException(ErrorCode.UNAUTHORIZED, "토큰이 만료되었습니다.");
        } catch (SecurityException | MalformedJwtException e) {
            throw new BadRequestException(ErrorCode.UNAUTHORIZED, "잘못된 JWT 서명입니다.");
        } catch (UnsupportedJwtException e) {
            throw new BadRequestException(ErrorCode.UNAUTHORIZED, "지원하지 않는 JWT 토큰입니다.");
        } catch (IllegalArgumentException e) {
            throw new BadRequestException(ErrorCode.BAD_REQUEST, "JWT 토큰이 잘못되었습니다.");
        }
    }
    }

