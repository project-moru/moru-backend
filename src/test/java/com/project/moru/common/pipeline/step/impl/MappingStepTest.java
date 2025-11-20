package com.project.moru.common.pipeline.step.impl;

import com.project.moru.common.pipeline.context.UserRegisterContext;
import com.project.moru.domain.dto.user.UserCreateRequestDto;
import com.project.moru.domain.entity.user.User;
import com.project.moru.mapper.struct.UserConverter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class MappingStepTest {
  
  @Mock
  private UserConverter converter;
  
  private MappingStep mappingStep;
  
  @BeforeEach
  void setUp() {
    mappingStep = new MappingStep(converter);
  }
  
  private UserCreateRequestDto createDto() {
    return new UserCreateRequestDto("user01", "Pass!123", "nick", null);
  }
  
  // ---------------------------------------------------------------------------
  // 1. 정상 케이스: Converter가 DTO → User 변환하고 context.setUser()로 저장되었는지
  // ---------------------------------------------------------------------------
  @Test
  void execute_shouldMapDtoToUserAndSetContextUser() {
    UserCreateRequestDto dto = createDto();
    UserRegisterContext context = new UserRegisterContext(dto);
    
    User mappedUser = new User();
    
    when(converter.fromCreateReqToEntity(dto)).thenReturn(mappedUser);
    
    mappingStep.execute(context);
    
    verify(converter, times(1)).fromCreateReqToEntity(dto);
    assertEquals(mappedUser, context.getUser());
  }
  
  // ---------------------------------------------------------------------------
  // 2. 실패 케이스: Converter가 예외를 던지면 Step도 동일 예외를 던져야 함
  // ---------------------------------------------------------------------------
  @Test
  void execute_shouldThrow_whenConverterFails() {
    UserCreateRequestDto dto = createDto();
    UserRegisterContext context = new UserRegisterContext(dto);
    
    when(converter.fromCreateReqToEntity(dto))
        .thenThrow(new IllegalStateException("mapping failed"));
    
    assertThrows(IllegalStateException.class, () -> mappingStep.execute(context));
  }
}