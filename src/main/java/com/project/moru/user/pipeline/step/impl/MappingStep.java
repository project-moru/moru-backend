package com.project.moru.user.pipeline.step.impl;

import com.project.moru.user.pipeline.Context;
import com.project.moru.user.pipeline.step.Step;
import com.project.moru.user.strategy.UserMappingStrategy;
import com.project.moru.user.domain.entity.User;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class MappingStep<T> implements Step<T> {
  
  private final UserMappingStrategy<T> strategy;
  
  @Override
  public void execute(Context<T> context) {
    User user = strategy.map(
        context.getDto(), context.getUser()
    );
    
    context.setUser(user);
  }
}