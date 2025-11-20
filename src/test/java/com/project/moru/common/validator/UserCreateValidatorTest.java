package com.project.moru.common.validator;

import com.project.moru.common.constant.Job;
import com.project.moru.domain.dto.user.UserCreateRequestDto;
import com.project.moru.domain.entity.user.User;
import com.project.moru.service.UserDataService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserCreateValidatorTest {
  @Mock
  private UserDataService userDataService;
  
  private UserCreateValidator validator;
  
  @BeforeEach
  void setUp() {
    validator = new UserCreateValidator(userDataService);
  }
  
  private UserCreateRequestDto createDto(
      String username,
      String password,
      String nickname,
      Job job
  ) {
    return new UserCreateRequestDto(username, password, nickname, job);
  }
  
  // -----------------------------------------------------------------------
  // 1. 실패 케이스: username 중복
  // -----------------------------------------------------------------------
  @Test
  void validate_shouldThrow_whenUsernameExists() {
    UserCreateRequestDto dto = createDto("testuser1", "Test!123", "nick", Job.DESIGNER);
    
    when(userDataService.findUserByUsername("testuser1"))
        .thenReturn(Optional.of(new User()));
    
    assertThrows(ValidationException.class, () -> validator.validate(dto));
  }
  
  // -----------------------------------------------------------------------
  // 2. 실패 케이스: nickname 중복
  // -----------------------------------------------------------------------
  @Test
  void validate_shouldThrow_whenNicknameExists() {
    UserCreateRequestDto dto = createDto("testuser1", "Test!123", "nick1", Job.RESEARCHER);
    
    when(userDataService.findUserByNickname("nick1"))
        .thenReturn(Optional.of(new User()));
    
    assertThrows(ValidationException.class, () -> validator.validate(dto));
  }
  
  // -----------------------------------------------------------------------
  // 3. 성공 케이스: 모든 값이 정상일 때 예외 발생하지 않아야 함
  // -----------------------------------------------------------------------
  @Test
  void validate_shouldPass_whenValidInput() {
    UserCreateRequestDto dto = createDto("validUser1", "Valid!123", "nick", Job.DESIGNER);
    
    when(userDataService.findUserByUsername("validUser1")).thenReturn(Optional.empty());
    when(userDataService.findUserByNickname("nick")).thenReturn(Optional.empty());
    
    assertDoesNotThrow(() -> validator.validate(dto));
  }
}