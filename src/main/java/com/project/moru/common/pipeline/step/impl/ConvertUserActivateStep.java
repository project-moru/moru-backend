package com.project.moru.common.pipeline.step.impl;

import com.project.moru.common.pipeline.context.UserContext;
import com.project.moru.common.pipeline.step.UserStep;
import com.project.moru.user.domain.entity.User;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ConvertUserActivateStep<T> implements UserStep<T>  {
  
  @Override
  public void execute(UserContext<T> context) {
    User user = context.getUser();
    user.convertUseYn();
  }
}