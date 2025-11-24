package com.project.moru.common.pipeline.step.impl;

import com.project.moru.common.pipeline.context.UserContext;
import com.project.moru.common.pipeline.step.UserStep;
import com.project.moru.user.service.UserDataService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class SaveStep<T> implements UserStep<T> {
  
  private final UserDataService userDataService;
  
  @Override
  public void execute(UserContext<T> context) {
    userDataService.saveUser(context.getUser());
  }
}
