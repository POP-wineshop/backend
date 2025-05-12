package com.popwine.backend.module.auth.security.util;

import com.popwine.backend.core.exception.BadRequestException;

import com.popwine.backend.core.exception.ErrorCode;
import com.popwine.backend.module.auth.security.CustomUserPrincipal;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class SecurityUtil {

    private SecurityUtil() {} // static only

    public static Long getCurrentUserId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || !authentication.isAuthenticated()) {
            throw new BadRequestException(ErrorCode.UNAUTHORIZED, "인증되지 않은 사용자입니다.");
        }

        Object principal = authentication.getPrincipal();

        if (!(principal instanceof CustomUserPrincipal)) {
            throw new BadRequestException(ErrorCode.UNAUTHORIZED, "인증되지 않은 사용자입니다.");
        }

        return ((CustomUserPrincipal) principal).getUserId();
    }

    public static String getCurrentUsername() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication.getName(); // subject
    }
}
