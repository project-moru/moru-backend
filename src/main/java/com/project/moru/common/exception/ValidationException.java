package com.project.moru.common.exception;

public class ValidationException extends GeneralException {
  public ValidationException(ErrorCode errorCode) {
    super(errorCode);
  }
}