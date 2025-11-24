package com.project.moru.common.strategy;

import com.project.moru.user.domain.dto.UserCreateRequestDto;
import com.project.moru.user.domain.entity.User;
import com.project.moru.user.mapper.struct.UserConverter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UserCreateMappingStrategy implements UserMappingStrategy<UserCreateRequestDto> {
  
  private final UserConverter converter;
  
  @Override
  public User map(UserCreateRequestDto dto, User user) {
    return converter.fromCreateReqToEntity(dto);
  }
}
