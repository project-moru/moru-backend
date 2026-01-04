package com.project.moru.user.service;

import com.project.moru.user.domain.dto.PwdChangeRequestDto;
import com.project.moru.user.domain.dto.UserCreateRequestDto;
import com.project.moru.user.domain.dto.UserResponseDto;
import com.project.moru.user.domain.dto.UserUpdateRequestDto;

import java.util.List;

public interface UserService {
  List<UserResponseDto> findAll();
  
  UserResponseDto findById(Long userId);
  
  UserResponseDto findByUsername(String username);
  
  void create(UserCreateRequestDto userCreateRequestDto);
  
  UserResponseDto update(Long userId, UserUpdateRequestDto userUpdateRequestDto);
  
  void pwdChange(Long userId, PwdChangeRequestDto pwdChangeRequestDto);
  
  void toggleUserUseYn(Long userId);
  
  void delete(Long userId);
}
