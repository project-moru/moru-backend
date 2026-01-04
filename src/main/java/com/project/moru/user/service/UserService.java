package com.project.moru.user.service;

import com.project.moru.user.domain.dto.*;

import java.util.List;

public interface UserService {
  List<UserResponseDto> findAll();
  
  UserResponseDto findById(Long userId);
  
  UserResponseDto findByUsername(String username);
  
  void create(UserCreateRequestDto userCreateRequestDto);
  
  UserResponseDto update(Long userId, UserUpdateRequestDto userUpdateRequestDto);
  
  void pwdChange(Long userId, PwdChangeRequestDto pwdChangeRequestDto);
  
  void dataFieldChange(Long userId, DataFieldChangeRequestDto dataFieldChangeRequestDto);
  
  void toggleUserUseYn(Long userId);
  
  void delete(Long userId);
}
