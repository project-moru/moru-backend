package com.project.moru.user.pipeline.step.impl;

import com.project.moru.user.pipeline.Context;
import com.project.moru.user.pipeline.step.Step;
import com.project.moru.user.validator.Validator;
import com.project.moru.user.domain.dto.UserValidatable;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ValidateStep<T extends UserValidatable> implements Step<T> {
  
  private final Validator<T> validator;
  
  @Override
  public void execute(Context<T> context) {
    validator.validate(context.getDto());
  }
}