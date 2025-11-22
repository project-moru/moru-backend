package com.project.moru.common.pipeline;

import com.project.moru.common.pipeline.context.UserUpdateContext;
import com.project.moru.common.pipeline.step.UserUpdateStep;
import com.project.moru.domain.dto.user.UserUpdateRequestDto;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;


@RequiredArgsConstructor
public class UserUpdatePipeline implements Pipeline {
  
  private final UserUpdateRequestDto dto;
  private final List<UserUpdateStep> steps = new ArrayList<>();
  
  public UserUpdatePipeline addStep(UserUpdateStep step) {
    steps.add(step);
    return this;
  }
  
  @Override
  public void execute() {
    UserUpdateContext context = new UserUpdateContext(dto);
    
    for (UserUpdateStep step : steps) {
      step.execute(context);
    }
  }
}