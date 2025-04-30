package com.popwine.backend.core.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {

    // 400
    INVALID_INPUT(400, "잘못된 요청입니다."),
    BAD_REQUEST(400, "잘못된 요청입니다."),

    // 401~403
    UNAUTHORIZED(401, "인증이 필요합니다."),
    FORBIDDEN(403, "접근 권한이 없습니다."),

    // 404
    ENTITY_NOT_FOUND(404, "존재하지 않는 리소스입니다."),
    WINE_NOT_FOUND(404, "해당 와인을 찾을 수 없습니다."),
    CATEGORY_NOT_FOUND(404, "해당 카테고리를 찾을 수 없습니다."),

    // 409
    DUPLICATE_RESOURCE(409, "이미 존재하는 리소스입니다."),
    INVALID_STATE(409, "잘못된 상태입니다."),

    // 500+
    DATABASE_ERROR(500, "데이터베이스 오류입니다."),
    INTERNAL_SERVER_ERROR(500, "서버 오류입니다."),
    SERVICE_UNAVAILABLE(503, "서비스를 일시적으로 사용할 수 없습니다.");

    private final int status;
    private final String message;
}
