package com.popwine.backend.core.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ErrorResponse {
    private final int status;
    private final String message;

    public static ErrorResponse from(ErrorCode errorCode) {
        return new ErrorResponse(errorCode.getStatus(), errorCode.getMessage());
    }

    public static ErrorResponse from(BaseException exception) {
        return new ErrorResponse(exception.getErrorCode().getStatus(), exception.getMessage());
    }
}
