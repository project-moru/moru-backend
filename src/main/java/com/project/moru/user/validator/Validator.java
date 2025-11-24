package com.project.moru.user.validator;

import com.project.moru.common.validator.ValidationException;
import com.project.moru.user.domain.dto.UserValidatable;
import com.project.moru.user.service.UserDataService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class Validator<T extends UserValidatable> {
  
  private final UserDataService userDataService;
  
  public void validate(T dto) {
    if (userDataService.findUserByUsername(dto.getUsername()).isPresent()) {
      throw new ValidationException("이미 존재하는 아이디입니다.");
    }
    
    if (userDataService.findUserByNickname(dto.getNickname()).isPresent()) {
      throw new ValidationException("이미 존재하는 닉네임입니다.");
    }
  }
}