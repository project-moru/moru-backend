package com.project.moru.common.pipeline.step.impl;

import com.project.moru.common.pipeline.context.UserRegisterContext;
import com.project.moru.domain.dto.user.UserCreateRequestDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class EncryptPasswordStepTest {
  
  @Mock
  private PasswordEncoder passwordEncoder;
  
  private EncryptPasswordStep step;
  
  @BeforeEach
  void setUp() {
    step = new EncryptPasswordStep(passwordEncoder);
  }
  
  private UserCreateRequestDto createDto() {
    return new UserCreateRequestDto("user01", "raw1234!", "nick", null);
  }
  
  // ---------------------------------------------------------------------------
  // 1. 정상 케이스: encode()가 호출되고 DTO의 비밀번호가 암호화된 값으로 변경되어야 함
  // ---------------------------------------------------------------------------
  @Test
  void execute_shouldEncryptPassword() {
    UserCreateRequestDto dto = createDto();
    UserRegisterContext context = new UserRegisterContext(dto);
    
    when(passwordEncoder.encode("raw1234!"))
        .thenReturn("ENCODED_PASSWORD");
    
    step.execute(context);
    
    verify(passwordEncoder, times(1)).encode("raw1234!");
    assertEquals("ENCODED_PASSWORD", dto.getPassword());
  }
  
  // ---------------------------------------------------------------------------
  // 2. 예외 케이스: PasswordEncoder가 예외를 던지면 Step도 그대로 예외를 던져야 함
  // ---------------------------------------------------------------------------
  @Test
  void execute_shouldThrow_whenPasswordEncoderFails() {
    UserCreateRequestDto dto = createDto();
    UserRegisterContext context = new UserRegisterContext(dto);
    
    when(passwordEncoder.encode("raw1234!"))
        .thenThrow(new IllegalStateException("encoding failed"));
    
    assertThrows(IllegalStateException.class, () -> step.execute(context));
  }
}