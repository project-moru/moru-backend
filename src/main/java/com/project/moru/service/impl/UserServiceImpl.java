package com.project.moru.service.impl;

import com.project.moru.domain.dto.user.UserCreateRequestDto;
import com.project.moru.domain.dto.user.UserResponseDto;
import com.project.moru.domain.dto.user.UserUpdateRequestDto;
import com.project.moru.domain.entity.user.User;
import com.project.moru.mapper.struct.UserConverter;
import com.project.moru.service.UserDataService;
import com.project.moru.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
  
  private final UserConverter userConverter;
  private final PasswordEncoder passwordEncoder;
  
  private final UserDataService userDataService;
  
  @Override
  public List<UserResponseDto> findAll() {
    return userConverter.toResList(userDataService.findAllUsers());
  }
  
  @Override
  public UserResponseDto findById(Long userId) {
    return userConverter.fromEntityToRes(
        userDataService.findUserById(userId).orElseThrow()
    );
  }
  
  @Override
  public UserResponseDto create(UserCreateRequestDto userCreateRequestDto) {
    String password = userCreateRequestDto.getPassword();
    userCreateRequestDto.setPassword(passwordEncoder.encode(password));
    
    User user = userDataService.saveUser(
        userConverter.fromCreateReqToEntity(userCreateRequestDto)
    );
    
    return userConverter.fromEntityToRes(user);
  }
  
  @Override
  public UserResponseDto update(Long id, UserUpdateRequestDto userUpdateRequestDto) {
    User user = userDataService.findUserById(id).orElseThrow();
    
    // 비밀번호 변경 시
    if (userUpdateRequestDto.getPassword() != null && !userUpdateRequestDto.getPassword().isBlank()) {
      user.updatePassword(passwordEncoder.encode(userUpdateRequestDto.getPassword()));
    }
    
    user.update(userUpdateRequestDto);
    
    return userConverter.fromEntityToRes(user);
  }
  
  @Override
  public void toggleUserUseYn(Long id) {
    User user = userDataService.findUserById(id).orElseThrow();
    
    user.convertUseYn();
  }
  
  @Override
  public void delete(Long userId) {
    userDataService.deleteUserById(userId);
  }
}