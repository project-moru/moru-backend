package com.project.moru.user.strategy;

import com.project.moru.domain.dto.user.UserUpdateRequestDto;
import com.project.moru.domain.entity.user.User;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UserUpdateMappingStrategy implements UserMappingStrategy<UserUpdateRequestDto> {
  
  @Override
  public User map(UserUpdateRequestDto dto, User existingUser) {
    
    existingUser.update(dto);
    
    return existingUser;
  }
}