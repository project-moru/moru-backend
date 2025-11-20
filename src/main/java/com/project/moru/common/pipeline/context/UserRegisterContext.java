package com.project.moru.common.pipeline.context;

import com.project.moru.domain.dto.user.UserCreateRequestDto;
import com.project.moru.domain.entity.user.User;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
@Getter
@Setter
public class UserRegisterContext {
  
  private final UserCreateRequestDto dto;
  private User user;
}