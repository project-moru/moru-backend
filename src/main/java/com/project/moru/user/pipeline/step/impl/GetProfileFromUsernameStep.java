package com.project.moru.user.pipeline.step.impl;

import com.project.moru.user.pipeline.Context;
import com.project.moru.user.pipeline.step.Step;
import com.project.moru.user.domain.entity.User;
import com.project.moru.user.service.UserDataService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class GetProfileFromUsernameStep<T> implements Step<T> {
  
  private final UserDataService userDataService;
  private final String username;
  
  @Override
  public void execute(Context<T> context) {
    User user = userDataService.findUserByUsername(username).orElseThrow();
    context.setUser(user);
  }
}
