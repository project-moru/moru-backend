package com.project.moru.service;

import com.project.moru.domain.dto.user.UserCreateRequestDto;
import com.project.moru.domain.dto.user.UserResponseDto;
import com.project.moru.domain.dto.user.UserUpdateRequestDto;

import java.util.List;

public interface UserService {
  List<UserResponseDto> findAll();
  
  UserResponseDto findById(Long userId);
  
  UserResponseDto save(UserCreateRequestDto userCreateRequestDto);
  
  UserResponseDto modify(Long id, UserUpdateRequestDto userUpdateRequestDto);
  
  void delete(Long userId);
}
