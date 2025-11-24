package com.project.moru.common.pipeline.step.impl;

import com.project.moru.common.pipeline.context.UserContext;
import com.project.moru.common.pipeline.step.UserStep;
import com.project.moru.user.domain.dto.PasswordChange;
import com.project.moru.user.domain.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;

@RequiredArgsConstructor
public class EncryptPasswordStep<T extends PasswordChange> implements UserStep<T> {
  
  private final PasswordEncoder encoder;
  
  @Override
  public void execute(UserContext<T> context) {
    String password = context.getDto().getPassword();
    
    if (password == null || password.isBlank()) {
      return;
    }
    
    User user = context.getUser();
    user.updatePassword(encoder.encode(context.getDto().getPassword()));
  }
}