package com.project.moru.common.pipeline.context;

import com.project.moru.domain.dto.user.UserUpdateRequestDto;
import com.project.moru.domain.entity.user.User;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
@Getter
@Setter
public class UserUpdateContext {
  
  private final UserUpdateRequestDto dto;
  private User user;
}