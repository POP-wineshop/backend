package com.popwine.backend.core.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {
    INVALID_INPUT(400, "잘못된 요청입니다"),
    ENTITY_NOT_FOUND(404, "존재하지 않는 리소스입니다."),
    INTERNAL_SERVER_ERROR(500, "서버 오류입니다.");


    private final int status;
    private final String message;

}
