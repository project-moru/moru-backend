package com.project.moru.service.impl;

import com.project.moru.domain.dto.user.UserCreateRequestDto;
import com.project.moru.domain.entity.user.User;
import com.project.moru.mapper.struct.UserConverter;
import com.project.moru.service.UserDataService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {
  
  @Mock private UserConverter userConverter;
  @Mock private UserCreateValidator validator;
  @Mock private PasswordEncoder passwordEncoder;
  @Mock private UserDataService userDataService;
  
  private UserServiceImpl userService;
  
  @BeforeEach
  void setUp() {
    userService = new UserServiceImpl(
        userConverter,
        passwordEncoder,
        validator,
        userDataService
    );
  }
  
  private UserCreateRequestDto createDto() {
    return new UserCreateRequestDto("user01", "Pass!123", "nick", null);
  }
  
  // ------------------------------------------------------------------------
  // 1. Service → Pipeline → Step 전체 호출 검증
  // ------------------------------------------------------------------------
  @Test
  void create_shouldRunAllSteps() {
    UserCreateRequestDto dto = createDto();
    User mappedUser = new User();
    
    when(passwordEncoder.encode("Pass!123"))
        .thenReturn("ENC_PASS");
    
    when(userConverter.fromCreateReqToEntity(dto))
        .thenReturn(mappedUser);
    
    userService.create(dto);
    
    // ValidateStep
    verify(validator, times(1)).validate(dto);
    
    // EncryptPasswordStep
    verify(passwordEncoder, times(1)).encode("Pass!123");
    
    // MappingStep
    verify(userConverter, times(1)).fromCreateReqToEntity(dto);
    
    // SaveStep
    verify(userDataService, times(1)).saveUser(mappedUser);
  }
}
