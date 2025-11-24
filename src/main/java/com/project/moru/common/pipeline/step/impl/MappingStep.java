package com.project.moru.common.pipeline.step.impl;

import com.project.moru.common.pipeline.context.UserContext;
import com.project.moru.common.pipeline.step.UserStep;
import com.project.moru.common.strategy.UserMappingStrategy;
import com.project.moru.user.domain.entity.User;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class MappingStep<T> implements UserStep<T> {
  
  private final UserMappingStrategy<T> strategy;
  
  @Override
  public void execute(UserContext<T> context) {
    User user = strategy.map(
        context.getDto(), context.getUser()
    );
    
    context.setUser(user);
  }
}