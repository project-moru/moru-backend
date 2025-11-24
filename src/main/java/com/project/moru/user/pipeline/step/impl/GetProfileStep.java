package com.project.moru.user.pipeline.step.impl;

import com.project.moru.user.pipeline.Context;
import com.project.moru.user.pipeline.step.Step;
import com.project.moru.domain.entity.user.User;
import com.project.moru.service.UserDataService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class GetProfileStep<T> implements Step<T> {
  
  private final UserDataService userDataService;
  private final Long userId;
  
  @Override
  public void execute(Context<T> context) {
    User user = userDataService.findUserById(userId).orElseThrow();
    context.setUser(user);
  }
}
