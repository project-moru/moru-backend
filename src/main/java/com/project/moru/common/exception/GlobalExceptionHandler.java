package com.project.moru.common.exception;

import com.project.moru.common.domain.dto.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(GeneralException.class)
    protected ResponseEntity<ErrorResponse> handleGeneralException(GeneralException e) {
        log.error("handleGeneralException", e);
        return ErrorResponse.toResponseEntity(e.getErrorCode());
    }
}
