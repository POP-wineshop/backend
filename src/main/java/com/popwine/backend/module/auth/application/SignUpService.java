package com.popwine.backend.module.auth.application;

import com.popwine.backend.core.exception.BadRequestException;
import com.popwine.backend.module.auth.controller.UserRequestDto;
import com.popwine.backend.module.auth.controller.UserResponseDto;
import com.popwine.backend.module.auth.domain.entity.User;
import com.popwine.backend.module.auth.domain.repository.UserRepository;
import com.popwine.backend.module.auth.domain.vo.Password;
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
    public UserResponseDto register(UserRequestDto dto) {

        if (userRepository.existsByUsername(dto.getUsername())) {
            throw new BadRequestException("이미 존재하는 사용자명입니다.");
        }

        Password password = new Password(dto.getPassword(), passwordEncoder);
        User user = dto.toEntity(password);
        User savedUser = userRepository.save(user);

        return UserResponseDto.from(savedUser);
    }

}
