package com.project.moru.common.pipeline.step.impl;

import com.project.moru.common.constant.Job;
import com.project.moru.common.pipeline.context.UserRegisterContext;
import com.project.moru.common.validator.UserCreateValidator;
import com.project.moru.common.validator.ValidationException;
import com.project.moru.domain.dto.user.UserCreateRequestDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ValidateStepTest {
  @Mock
  private UserCreateValidator validator;
  
  private ValidateStep validateStep;
  
  @BeforeEach
  void setUp() {
    validateStep = new ValidateStep(validator);
  }
  
  private UserCreateRequestDto createDto() {
    return new UserCreateRequestDto("testuser1", "Test!123", "nick", Job.DESIGNER);
  }
  
  // ---------------------------------------------------------------------------
  // 1. 정상 케이스: ValidateStep 실행 시 validator.validate(dto)가 호출되어야 한다
  // ---------------------------------------------------------------------------
  @Test
  void execute_shouldCallValidator() {
    UserCreateRequestDto dto = createDto();
    UserRegisterContext context = new UserRegisterContext(dto);
    
    validateStep.execute(context);
    
    verify(validator, times(1)).validate(dto);
  }
  
  // ---------------------------------------------------------------------------
  // 2. 실패 케이스: Validator가 예외를 던지면 Step도 예외를 그대로 던져야 한다
  // ---------------------------------------------------------------------------
  @Test
  void execute_shouldThrow_whenValidatorThrowsException() {
    UserCreateRequestDto dto = createDto();
    UserRegisterContext context = new UserRegisterContext(dto);
    
    doThrow(new ValidationException("중복 아이디"))
        .when(validator)
        .validate(dto);
    
    assertThrows(ValidationException.class, () -> validateStep.execute(context));
  }
}
