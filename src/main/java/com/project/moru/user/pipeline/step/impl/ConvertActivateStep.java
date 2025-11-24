package com.project.moru.user.pipeline.step.impl;

import com.project.moru.user.pipeline.Context;
import com.project.moru.user.pipeline.step.Step;
import com.project.moru.domain.entity.user.User;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ConvertActivateStep<T> implements Step<T> {
  
  @Override
  public void execute(Context<T> context) {
    User user = context.getUser();
    user.convertUseYn();
  }
}