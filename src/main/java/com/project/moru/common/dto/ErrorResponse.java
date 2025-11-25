package com.project.moru.common.dto;

import com.project.moru.common.exception.ErrorCode;
import lombok.Builder;
import lombok.Getter;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;

@Getter
@Builder
public class ErrorResponse {
    private final LocalDateTime timestamp = LocalDateTime.now();
    private final int status;
    private final String error;
    private final String message;

    // ErrorCode를 받아서 ResponseEntity<ErrorResponse>로 변환해주는 static 메서드
    public static ResponseEntity<ErrorResponse> toResponseEntity(ErrorCode errorCode) {
        return ResponseEntity
                .status(errorCode.getCode())
                .body(ErrorResponse.builder()
                        .status(errorCode.getCode())
                        .error(errorCode.name())
                        .message(errorCode.getMessage())
                        .build()
                );
    }
}