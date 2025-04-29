package com.popwine.backend.core.response;

import lombok.Getter;

@Getter
public class ApiResponse<T> {

    private int status;   // HTTP 상태 코드
    private String message; // 응답 메시지 (성공/실패 안내)
    private T data;       // 실제 응답 데이터

    // 성공 응답 생성 (데이터 포함)
    public static <T> ApiResponse<T> success(T data) {
        return new ApiResponse<>(200, "성공", data);
    }

    // 성공 응답 생성 (데이터 없음)
    public static ApiResponse<Void> success() {
        return new ApiResponse<>(200, "성공", null);
    }


    // 실패 응답 생성 (데이터 없이)
    public static <T> ApiResponse<T> fail(int status, String message) {
        return new ApiResponse<>(status, message, null);
    }



    public ApiResponse(int status, String message, T data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }
}
