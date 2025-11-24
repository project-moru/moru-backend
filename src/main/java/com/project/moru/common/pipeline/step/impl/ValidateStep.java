package com.project.moru.common.pipeline.step.impl;

import com.project.moru.common.pipeline.context.UserContext;
import com.project.moru.common.pipeline.step.UserStep;
import com.project.moru.common.validator.Validator;
import com.project.moru.user.domain.dto.UserValidatable;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ValidateStep<T extends UserValidatable> implements UserStep<T> {
  
  private final Validator<T> validator;
  
  @Override
  public void execute(UserContext<T> context) {
    validator.validate(context.getDto());
  }
}