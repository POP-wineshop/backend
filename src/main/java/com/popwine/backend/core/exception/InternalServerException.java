package com.popwine.backend.core.exception;

public class InternalServerException extends BaseException {

    public InternalServerException(String message) {
        super(ErrorCode.INTERNAL_SERVER_ERROR, message);
    }

    public InternalServerException(ErrorCode errorCode, String message) {
        super(errorCode, message);
    }

    public InternalServerException(ErrorCode errorCode) {
        super(errorCode);
    }

    public InternalServerException(String message, Throwable cause) {
        super(ErrorCode.INTERNAL_SERVER_ERROR, message);
        initCause(cause);
    }
}
