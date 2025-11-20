package com.project.moru.common.pipeline.step.impl;

import com.project.moru.common.pipeline.context.UserRegisterContext;
import com.project.moru.common.pipeline.step.UserRegisterStep;
import com.project.moru.domain.dto.user.UserCreateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;

@RequiredArgsConstructor
public class EncryptPasswordStep implements UserRegisterStep {
  
  private final PasswordEncoder encoder;
  
  @Override
  public void execute(UserRegisterContext context) {
    UserCreateRequestDto dto = context.getDto();
    dto.setPassword(encoder.encode(dto.getPassword()));
  }
}
