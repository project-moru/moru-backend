package com.project.moru.common.pipeline.step.impl;

import com.project.moru.common.pipeline.context.UserContext;
import com.project.moru.common.pipeline.step.UserStep;
import com.project.moru.user.domain.entity.User;
import com.project.moru.user.service.UserDataService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class GetUserProfileFromUsernameStep<T> implements UserStep<T> {
  
  private final UserDataService userDataService;
  private final String username;
  
  @Override
  public void execute(UserContext<T> context) {
    User user = userDataService.findUserByUsername(username).orElseThrow();
    context.setUser(user);
  }
}
