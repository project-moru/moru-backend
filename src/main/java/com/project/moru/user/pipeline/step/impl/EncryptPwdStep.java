package com.project.moru.user.pipeline.step.impl;

import com.project.moru.user.pipeline.Context;
import com.project.moru.user.pipeline.step.Step;
import com.project.moru.user.domain.dto.EncryptPwd;
import com.project.moru.user.domain.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;

@RequiredArgsConstructor
public class EncryptPwdStep<T extends EncryptPwd> implements Step<T> {
  
  private final PasswordEncoder encoder;
  
  @Override
  public void execute(Context<T> context) {
    String password = context.getDto().getPassword();
    
    if (password == null || password.isBlank()) {
      return;
    }
    
    User user = context.getUser();
    user.updatePassword(encoder.encode(password));
  }
}