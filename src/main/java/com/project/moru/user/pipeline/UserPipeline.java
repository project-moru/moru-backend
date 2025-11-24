package com.project.moru.user.pipeline;

import com.project.moru.user.pipeline.step.Step;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;


@RequiredArgsConstructor
public class UserPipeline<T> implements Pipeline<T> {
  
  private final T dto;
  private final List<Step<T>> steps = new ArrayList<>();
  
  public UserPipeline<T> addStep(Step<T> step) {
    steps.add(step);
    return this;
  }
  
  @Override
  public Context<T> execute() {
    Context<T> context = new Context<>(dto);
    
    for (Step<T> step : steps) {
      step.execute(context);
    }
    
    return context;
  }
}