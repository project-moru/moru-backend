package com.project.moru.user.pipeline.step.impl;

import com.project.moru.common.exception.ErrorCode;
import com.project.moru.common.exception.GeneralException;
import com.project.moru.user.domain.dto.ChangePwd;
import com.project.moru.user.domain.entity.User;
import com.project.moru.user.pipeline.Context;
import com.project.moru.user.pipeline.step.Step;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;

@RequiredArgsConstructor
public class PwdChangeStep<T extends ChangePwd> implements Step<T> {
  
  private final PasswordEncoder encoder;
  
  @Override
  public void execute(Context<T> context) {
    String beforePwd = context.getDto().getBeforePassword();
    String updatePwd = context.getDto().getUpdatePassword();
    
    User user = context.getUser();
    
    if (!encoder.matches(beforePwd, user.getPassword())) {
      throw new GeneralException(ErrorCode.INVALID_PASSWORD);
    } else if (encoder.matches(updatePwd, user.getPassword())) {
      throw new GeneralException(ErrorCode.DUPLICATE_PASSWORD);
    }
    
    user.updatePassword(encoder.encode(updatePwd));
    
  }
}
