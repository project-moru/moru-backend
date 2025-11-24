package com.project.moru.service;

import com.project.moru.domain.dto.user.UserCreateRequestDto;
import com.project.moru.domain.dto.user.UserResponseDto;
import com.project.moru.domain.dto.user.UserUpdateRequestDto;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface UserService {
  @Transactional(readOnly = true) List<UserResponseDto> findAll();
  
  @Transactional(readOnly = true) UserResponseDto findByUsername(String username);
  
  @Transactional void create(UserCreateRequestDto userCreateRequestDto);
  
  @Transactional UserResponseDto update(Long id, UserUpdateRequestDto userUpdateRequestDto);
  
  @Transactional void toggleUserUseYn(Long id);
  
  @Transactional void delete(Long userId);
}
