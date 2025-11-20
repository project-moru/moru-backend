package com.project.moru.common.pipeline.step.impl;

import com.project.moru.common.pipeline.context.UserRegisterContext;
import com.project.moru.common.pipeline.step.UserRegisterStep;
import com.project.moru.common.validator.UserCreateValidator;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ValidateStep implements UserRegisterStep {
  
  private final UserCreateValidator userCreateValidator;
  
  @Override
  public void execute(UserRegisterContext context) {
    userCreateValidator.validate(context.getDto());
  }
}