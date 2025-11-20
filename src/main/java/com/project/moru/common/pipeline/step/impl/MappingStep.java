package com.project.moru.common.pipeline.step.impl;

import com.project.moru.common.pipeline.context.UserRegisterContext;
import com.project.moru.common.pipeline.step.UserRegisterStep;
import com.project.moru.domain.entity.user.User;
import com.project.moru.mapper.struct.UserConverter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class MappingStep implements UserRegisterStep {
  
  private final UserConverter converter;
  
  @Override
  public void execute(UserRegisterContext context) {
    User user = converter.fromCreateReqToEntity(
        context.getDto()
    );
    
    context.setUser(user);
  }
}