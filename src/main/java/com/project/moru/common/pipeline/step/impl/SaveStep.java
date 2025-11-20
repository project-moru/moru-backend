package com.project.moru.common.pipeline.step.impl;

import com.project.moru.common.pipeline.context.UserRegisterContext;
import com.project.moru.common.pipeline.step.UserRegisterStep;
import com.project.moru.service.UserDataService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class SaveStep implements UserRegisterStep {
  
  private final UserDataService userDataService;
  
  @Override
  public void execute(UserRegisterContext context) {
    userDataService.saveUser(context.getUser());
  }
}
