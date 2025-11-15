package com.project.moru.service.impl;

import com.project.moru.common.jwt.JwtTokenProvider;
import com.project.moru.domain.dto.auth.RefreshTokenDto;
import com.project.moru.domain.dto.auth.RefreshTokenResponseDto;
import com.project.moru.domain.dto.auth.LoginRequestDto;
import com.project.moru.domain.dto.auth.LoginResultDto;
import com.project.moru.domain.entity.user.CustomUserDetails;
import com.project.moru.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
  
  private final AuthenticationManager authenticationManager;
  private final JwtTokenProvider jwtTokenProvider;
  private final StringRedisTemplate redisTemplate;
  
  private final long accessTokenValidity = 3600000;
  private final long refreshTokenValidity = 25200000;
  
  
  @Override
  public LoginResultDto login(LoginRequestDto loginRequestDto) {
    
    // [private] authenticateByUsernamePassword 메서드 호출 : UserDetails 인스턴스 반환
    CustomUserDetails userDetails = authenticateByUsernamePassword(
        loginRequestDto.getUsername(), loginRequestDto.getPassword()
    );
    
    // access, refresh token 생성
    String accessToken = jwtTokenProvider.generateAccessToken(userDetails.getUsername());
    String refreshToken = jwtTokenProvider.generateRefreshToken(userDetails.getUsername());
    
    // [private] saveRefreshToken 메서드 호출 : redis에 refresh token 저장
    saveRefreshToken(
        RefreshTokenDto.builder()
            .username(userDetails.getUsername())
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
    String username = jwtTokenProvider.getUsername(accessToken);
    
    // redis 사용자 refresh token 삭제
    redisTemplate.delete(username);
  }
  
  @Override
  public RefreshTokenResponseDto refresh(String refreshToken){
    
    if (!jwtTokenProvider.validateToken(refreshToken)) {
      throw new RuntimeException("유효하지 않은 토큰 입니다.");
    }
    
    String username = jwtTokenProvider.getUsername(refreshToken);
    String storedRefreshToken = redisTemplate.opsForValue().get(username);
    
    if (storedRefreshToken == null || !storedRefreshToken.equals(refreshToken)) {
      throw new RuntimeException("유효하지 않은 리프레시 토큰입니다.");
    }
    
    String newAccess = jwtTokenProvider.generateAccessToken(username);
    String newRefresh = jwtTokenProvider.generateRefreshToken(username);
    
    redisTemplate.delete(username);
    
    redisTemplate.opsForValue().set( username, newRefresh, refreshTokenValidity, TimeUnit.MILLISECONDS);
    
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
    redisTemplate.opsForValue().set(
        refreshTokenDto.getUsername(),
        refreshTokenDto.getRefreshToken(),
        refreshTokenDto.getDuration(),
        refreshTokenDto.getUnit()
    );
  }
}