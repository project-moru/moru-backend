package com.project.moru.auth.service;

import com.project.moru.auth.domain.dto.LoginRequestDto;
import com.project.moru.auth.domain.dto.LoginResultDto;
import com.project.moru.auth.domain.dto.RefreshTokenResponseDto;

import javax.servlet.http.HttpServletRequest;

public interface AuthService {
  LoginResultDto login(LoginRequestDto loginRequestDto);
  
  void logout(HttpServletRequest request);
  
  RefreshTokenResponseDto refresh(String refreshToken);
}
