package com.project.moru.user.pipeline.step.impl;

import com.project.moru.user.pipeline.Context;
import com.project.moru.user.pipeline.step.Step;
import com.project.moru.user.service.UserDataService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class SaveStep<T> implements Step<T> {
  
  private final UserDataService userDataService;
  
  @Override
  public void execute(Context<T> context) {
    userDataService.saveUser(context.getUser());
  }
}
