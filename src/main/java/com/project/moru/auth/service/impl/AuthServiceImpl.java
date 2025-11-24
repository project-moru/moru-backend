package com.project.moru.auth.service.impl;

import com.project.moru.common.jwt.JwtTokenProvider;
import com.project.moru.auth.domain.dto.RefreshTokenDto;
import com.project.moru.auth.domain.dto.RefreshTokenResponseDto;
import com.project.moru.auth.domain.dto.LoginRequestDto;
import com.project.moru.auth.domain.dto.LoginResultDto;
import com.project.moru.user.domain.entity.CustomUserDetails;
import com.project.moru.auth.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
  
  private final AuthDataServiceImpl authDataService;
  private final AuthenticationManager authenticationManager;
  private final JwtTokenProvider jwtTokenProvider;
  
  @Override
  public LoginResultDto login(LoginRequestDto loginRequestDto) {
    
    // [private] authenticateByUsernamePassword 메서드 호출 : UserDetails 인스턴스 반환
    CustomUserDetails userDetails = authenticateByUsernamePassword(
        loginRequestDto.getUsername(), loginRequestDto.getPassword()
    );
    
    // access, refresh token 생성
    String accessToken = jwtTokenProvider.generateAccessToken(userDetails.getUserId());
    String refreshToken = jwtTokenProvider.generateRefreshToken(userDetails.getUserId());
    
    // [private] saveRefreshToken 메서드 호출 : redis에 refresh token 저장
    saveRefreshToken(
        RefreshTokenDto.builder()
            .userId(userDetails.getUserId())
            .refreshToken(refreshToken)
            .duration(7)
            .unit(TimeUnit.DAYS)
            .build()
    );
    
    return LoginResultDto.builder()
        .username(userDetails.getUsername())
        .nickname(userDetails.getNickname())
        .accessToken(accessToken)
        .refreshToken(refreshToken)
        .build();
  }
  
  @Override
  public void logout(HttpServletRequest request) {
    String accessToken = jwtTokenProvider.resolveToken(request);
    Long userId = jwtTokenProvider.getUserId(accessToken);
    
    // redis 사용자 refresh token 삭제
    authDataService.deleteRefreshToken(userId);
  }
  
  @Override
  public RefreshTokenResponseDto refresh(String refreshToken){
    
    if (!jwtTokenProvider.validateToken(refreshToken)) {
      throw new RuntimeException("유효하지 않은 토큰 입니다.");
    }
    
    Long userId = jwtTokenProvider.getUserId(refreshToken);
    String storedRefreshToken = authDataService.findRefreshToken(userId);
    
    if (storedRefreshToken == null || !storedRefreshToken.equals(refreshToken)) {
      throw new RuntimeException("유효하지 않은 리프레시 토큰입니다.");
    }
    
    String newAccess = jwtTokenProvider.generateAccessToken(userId);
    String newRefresh = jwtTokenProvider.generateRefreshToken(userId);
    authDataService.deleteRefreshToken(userId);
    authDataService.saveRefreshToken(
        userId, newRefresh, 7, TimeUnit.DAYS
    );
    
    return RefreshTokenResponseDto.builder()
        .accessToken(newAccess)
        .refreshToken(newRefresh)
        .build();
  }
  
  
  private CustomUserDetails authenticateByUsernamePassword(String username, String password) {
    Authentication authentication = authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(username, password)
    );
    
    return (CustomUserDetails) authentication.getPrincipal();
  }
  
  private void saveRefreshToken(RefreshTokenDto refreshTokenDto) {
    authDataService.saveRefreshToken(
        refreshTokenDto.getUserId(),
        refreshTokenDto.getRefreshToken(),
        refreshTokenDto.getDuration(),
        refreshTokenDto.getUnit()
    );
  }
}