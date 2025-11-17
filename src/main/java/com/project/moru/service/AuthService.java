package com.project.moru.service;

import com.project.moru.domain.dto.auth.LoginRequestDto;
import com.project.moru.domain.dto.auth.LoginResultDto;
import com.project.moru.domain.dto.auth.RefreshTokenResponseDto;

import javax.servlet.http.HttpServletRequest;

public interface AuthService {
  LoginResultDto login(LoginRequestDto loginRequestDto);
  
  void logout(HttpServletRequest request);
  
  RefreshTokenResponseDto refresh(String refreshToken);
}
