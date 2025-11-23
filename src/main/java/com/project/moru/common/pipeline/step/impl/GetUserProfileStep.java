package com.project.moru.common.pipeline.step.impl;

import com.project.moru.common.pipeline.context.UserContext;
import com.project.moru.common.pipeline.step.UserStep;
import com.project.moru.domain.entity.user.User;
import com.project.moru.service.UserDataService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class GetUserProfileStep<T> implements UserStep<T> {
  
  private final UserDataService userDataService;
  private final Long userId;
  
  @Override
  public void execute(UserContext<T> context) {
    User user = userDataService.findUserById(userId).orElseThrow();
    context.setUser(user);
  }
}
