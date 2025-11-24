package com.project.moru.common.strategy;

import com.project.moru.user.domain.dto.UserUpdateRequestDto;
import com.project.moru.user.domain.entity.User;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UserUpdateMappingStrategy implements UserMappingStrategy<UserUpdateRequestDto> {
  
  @Override
  public User map(UserUpdateRequestDto dto, User existingUser) {
    
    existingUser.update(dto);
    
    return existingUser;
  }
}