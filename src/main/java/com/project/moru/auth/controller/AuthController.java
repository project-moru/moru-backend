package com.project.moru.auth.controller;

import com.project.moru.common.utils.CookieUtils;
import com.project.moru.common.utils.ApiResponse;
import com.project.moru.auth.domain.dto.RefreshTokenResponseDto;
import com.project.moru.auth.domain.dto.LoginRequestDto;
import com.project.moru.auth.domain.dto.LoginResponseDto;
import com.project.moru.auth.domain.dto.LoginResultDto;
import com.project.moru.auth.service.AuthService;
import com.project.moru.user.domain.dto.UserCreateRequestDto;
import com.project.moru.user.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@Tag(name = "auth", description = "인증 API")
@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
  
  private final AuthService authService;
  private final UserService userService;
  
  @Operation(summary = "회원가입 API")
  @PostMapping("/register")
  public ResponseEntity<ApiResponse<Void>> create(
      @Valid @RequestBody UserCreateRequestDto userCreateRequestDto
  ) {
    
    userService.create(userCreateRequestDto);
    
    return ResponseEntity.ok().body(ApiResponse.ok(201, "Created"));
  }
  
  @Operation(summary = "로그인 API")
  @PostMapping("/login")
  public ResponseEntity<ApiResponse<LoginResponseDto>> login(
      @RequestBody LoginRequestDto request,
      HttpServletResponse httpResponse) {
    
    // authService.login 호출
    LoginResultDto loginResultDto = authService.login(request);
    
    // httpOnly Cookie 생성 : refresh token 저장 용도
    ResponseCookie refreshCookie = CookieUtils.createRefreshTokenCookie(loginResultDto.getRefreshToken());
    httpResponse.setHeader(HttpHeaders.SET_COOKIE, refreshCookie.toString());
    
    return ResponseEntity.ok().body(ApiResponse.ok(loginResultDto.toResponseDto()));
  }
  
  @Operation(summary = "로그아웃 API")
  @SecurityRequirement(name = "bearerAuth")
  @PostMapping("/logout")
  public  ResponseEntity<ApiResponse<Void>> logout(HttpServletRequest request, HttpServletResponse httpResponse) {

    // authService.logout 호출
    authService.logout(request);

    // refresh token 쿠키 삭제
    ResponseCookie expiredCookie = CookieUtils.expiredCookie();
    httpResponse.setHeader(HttpHeaders.SET_COOKIE, expiredCookie.toString());

//    return ResponseEntity.ok().build();
    return ResponseEntity.ok().body(ApiResponse.ok());
  }

  // access token 만료 시 재발급 요청 API 구현 필요
  // TODO: 2025/11/1  - Nano 구현완료
  @Operation(summary = "토큰 재발급용 API")
  @PostMapping("/refresh")
  public ResponseEntity<ApiResponse<RefreshTokenResponseDto>> refresh
    (
            @RequestParam("refreshToken") String refreshToken
    ) {
    return ResponseEntity.ok().body(ApiResponse.ok(authService.refresh(refreshToken)));
  }

}
