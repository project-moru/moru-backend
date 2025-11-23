package com.project.moru.common.pipeline;

import com.project.moru.common.pipeline.context.UserContext;
import com.project.moru.common.pipeline.step.UserStep;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;


@RequiredArgsConstructor
public class UserPipeline<T> implements Pipeline {
  
  private final T dto;
  private final List<UserStep<T>> steps = new ArrayList<>();
  
  public UserPipeline<T> addStep(UserStep<T> step) {
    steps.add(step);
    return this;
  }
  
  @Override
  public UserContext<T> execute() {
    UserContext<T> context = new UserContext<>(dto);
    
    for (UserStep<T> step : steps) {
      step.execute(context);
    }
    
    return context;
  }
}