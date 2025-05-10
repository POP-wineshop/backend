package com.popwine.backend.core.exception;

public class BadRequestException extends BaseException {

    public BadRequestException(String message) {
        super(ErrorCode.BAD_REQUEST, message);
    }

    public BadRequestException(ErrorCode errorCode, String message) {
        super(errorCode, message);
    }

    public BadRequestException(ErrorCode errorCode) {
        super(errorCode);
    }
}
