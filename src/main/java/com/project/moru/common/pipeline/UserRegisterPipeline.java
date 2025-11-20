package com.project.moru.common.pipeline;

import com.project.moru.common.pipeline.context.UserRegisterContext;
import com.project.moru.common.pipeline.step.UserRegisterStep;
import com.project.moru.domain.dto.user.UserCreateRequestDto;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;


@RequiredArgsConstructor
public class UserRegisterPipeline implements Pipeline {
  
  private final UserCreateRequestDto dto;
  private final List<UserRegisterStep> steps = new ArrayList<>();
  
  public UserRegisterPipeline addStep(UserRegisterStep step) {
    steps.add(step);
    return this;
  }
  
  @Override
  public void execute() {
    UserRegisterContext context = new UserRegisterContext(dto);
    
    for (UserRegisterStep step : steps) {
      step.execute(context);
    }
  }
}