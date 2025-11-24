package com.project.moru.user.strategy;

import com.project.moru.domain.dto.user.UserCreateRequestDto;
import com.project.moru.domain.entity.user.User;
import com.project.moru.mapper.struct.UserConverter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UserCreateMappingStrategy implements UserMappingStrategy<UserCreateRequestDto> {
  
  private final UserConverter converter;
  
  @Override
  public User map(UserCreateRequestDto dto, User user) {
    return converter.fromCreateReqToEntity(dto);
  }
}
