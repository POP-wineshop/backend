package com.popwine.backend.module.auth.vo;

import com.popwine.backend.core.exception.BadRequestException;
import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Objects;
import java.util.regex.Pattern;


@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Password {

    private static final String PASSWORD_REGEX = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[!@#$%^&*(),.?\":{}|<>])[A-Za-z\\d!@#$%^&*(),.?\":{}|<>]{8,20}$";
    private static final Pattern PASSWORD_PATTERN = Pattern.compile(PASSWORD_REGEX);

    private String password;

    public Password(String password, PasswordEncoder encoder) {
        validate(password);
        this.password = encoder.encode(password);
    }
    /**
     * 비밀번호 유효성 검증
     *
     * @param value 검증할 비밀번호 값
     */
    private void validate(String value) {
        if (value == null || value.isBlank()) {
            throw new BadRequestException("비밀번호는 필수 값 입니다.");
        }
        if (!PASSWORD_PATTERN.matcher(value).matches()) {
            throw new BadRequestException("영문, 숫자, 특수문자를 포함한 8~20자의 비밀번호를 입력해주세요.");
        }
    }

    /**
     * 평문 비밀번호와 일치 여부 확인
     *
     * @param rawPassword 검증할 평문 비밀번호
     * @param encoder 비밀번호 인코더
     * @return 일치 여부
     */
    public boolean matches(String rawPassword, PasswordEncoder encoder) {
        return encoder.matches(rawPassword, password);
    }

    /**
     * 해시 코드 생성
     *
     * @return 해시 코드 값
     */
    @Override
    public int hashCode() {
        return Objects.hash(password);
    }

    /**
     * 문자열 반환
     *
     * @return 비밀번호 문자열
     */
    @Override
    public String toString() {
        return password;
    }
}

