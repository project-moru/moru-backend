package com.project.moru.common.pipeline.step.impl;

import com.project.moru.common.pipeline.context.UserRegisterContext;
import com.project.moru.domain.dto.user.UserCreateRequestDto;
import com.project.moru.domain.entity.user.User;
import com.project.moru.service.UserDataService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class SaveStepTest {
  @Mock
  private UserDataService userDataService;
  
  private SaveStep saveStep;
  
  @BeforeEach
  void setUp() {
    saveStep = new SaveStep(userDataService);
  }
  
  private User createUser() {
    User user = new User();
    return user;
  }
  
  // ---------------------------------------------------------------------------
  // 1. 정상 케이스: saveUser()가 정확히 1번 호출되는지
  // ---------------------------------------------------------------------------
  @Test
  void execute_shouldCallSaveUser() {
    User user = createUser();
    UserRegisterContext context = new UserRegisterContext(null);
    context.setUser(user);
    
    saveStep.execute(context);
    
    verify(userDataService, times(1)).saveUser(user);
  }
  
  // ---------------------------------------------------------------------------
  // 2. 실패 케이스: saveUser가 예외를 던지면 Step도 그대로 예외를 던져야 함
  // ---------------------------------------------------------------------------
  @Test
  void execute_shouldThrow_whenSaveUserFails() {
    User user = createUser();
    UserRegisterContext context = new UserRegisterContext(null);
    context.setUser(user);
    
    doThrow(new IllegalStateException("DB 실패"))
        .when(userDataService)
        .saveUser(user);
    
    assertThrows(IllegalStateException.class, () -> saveStep.execute(context));
  }
}
