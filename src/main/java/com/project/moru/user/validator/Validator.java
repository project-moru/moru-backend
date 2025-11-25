package com.project.moru.user.validator;

import com.project.moru.common.exception.ErrorCode;
import com.project.moru.common.exception.ValidationException;
import com.project.moru.user.domain.dto.UserValidatable;
import com.project.moru.user.service_data.UserDataService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class Validator<T extends UserValidatable> {
  
  private final UserDataService userDataService;
  
  public void validate(T dto) {
    if (userDataService.findUserByUsername(dto.getUsername()).isPresent()) {
      throw new ValidationException(ErrorCode.DUPLICATE_USERNAME);
    }
    
    if (userDataService.findUserByNickname(dto.getNickname()).isPresent()) {
      throw new ValidationException(ErrorCode.DUPLICATE_NICKNAME);
    }
  }
}