package com.project.moru.service;

import com.project.moru.domain.dto.auth.LoginRequestDto;
import com.project.moru.domain.dto.auth.LoginResultDto;
import com.project.moru.domain.dto.auth.RefreshTokenDto;
import com.project.moru.domain.dto.auth.RefreshTokenResponseDto;
import com.project.moru.domain.entity.user.CustomUserDetails;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

import javax.servlet.http.HttpServletRequest;

public interface AuthService {
  LoginResultDto login(LoginRequestDto loginRequestDto);
  
  void logout(HttpServletRequest request);
  
  default CustomUserDetails authenticateByUsernamePassword(String username, String password) {
    Authentication authentication = authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(username, password)
    );
    
    return (CustomUserDetails) authentication.getPrincipal();
  }
  
  default void saveRefreshToken(RefreshTokenDto refreshTokenDto) {
    redisTemplate.opsForValue().set(
        refreshTokenDto.getUsername(),
        refreshTokenDto.getRefreshToken(),
        refreshTokenDto.getDuration(),
        refreshTokenDto.getUnit()
    );
  }
  
  RefreshTokenResponseDto refresh(String refreshToken);
}
