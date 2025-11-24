package com.project.moru.card.service.impl;

import com.project.moru.user.domain.dto.UserCreateRequestDto;
import com.project.moru.user.domain.entity.User;
import com.project.moru.user.mapper.struct.UserConverter;
import com.project.moru.user.service.UserDataService;
import com.project.moru.user.service.impl.UserServiceImpl;
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
