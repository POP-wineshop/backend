package com.popwine.backend.module.auth.application;

import com.popwine.backend.core.exception.BadRequestException;
import com.popwine.backend.module.auth.api.dto.SignUpRequestDto;
import com.popwine.backend.module.auth.api.dto.SignUpResponseDto;
import com.popwine.backend.module.auth.domain.entity.User;
import com.popwine.backend.module.auth.domain.repo.UserRepository;
import com.popwine.backend.module.auth.domain.vo.Password;
import com.popwine.backend.module.auth.domain.vo.Username;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class SignUpService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public SignUpResponseDto signup(SignUpRequestDto dto) {
        Username username = new Username(dto.getUsername());

        // 사용자명 중복 검사
        if (userRepository.existsByUsername(username.getValue())) {
            throw new BadRequestException("이미 존재하는 사용자명입니다.");
        }

        // 비밀번호 인코딩 및 중복 검사 후 엔티티 생성
        Password password = new Password(dto.getPassword(), passwordEncoder);
        User user = dto.toEntity(password);

        User savedUser = userRepository.save(user);
        return SignUpResponseDto.from(savedUser);
    }
}
