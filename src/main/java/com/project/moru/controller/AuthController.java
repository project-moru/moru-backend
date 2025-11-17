package com.project.moru.controller;

import com.project.moru.common.utils.CookieUtils;
import com.project.moru.common.utils.ApiResponse;
import com.project.moru.domain.dto.auth.RefreshTokenResponseDto;
import com.project.moru.domain.dto.auth.LoginRequestDto;
import com.project.moru.domain.dto.auth.LoginResponseDto;
import com.project.moru.domain.dto.auth.LoginResultDto;
import com.project.moru.service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
  
  private final AuthService authService;
  
  @Operation(summary = "로그인 API")
//  @ApiResponses({
//      @ApiResponse(responseCode = "200", description = "로그인 성공"),
//      @ApiResponse(responseCode = "400", description = "요청 형식 오류")
//  })
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
//  @ApiResponses({
//      @ApiResponse(responseCode = "200", description = "로그아웃 성공"),
//      @ApiResponse(responseCode = "400", description = "요청 형식 오류")
//  })
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
//  @ApiResponses({
//          @ApiResponse(responseCode = "200", description = "토큰 발급 성공"),
//          @ApiResponse(responseCode = "400", description = "요청 형식 오류")
//  })
  @PostMapping("/refresh")
  public ResponseEntity<ApiResponse<RefreshTokenResponseDto>> refresh
    (
            @RequestParam("refreshToken") String refreshToken
    ) {
    return ResponseEntity.ok().body(ApiResponse.ok(authService.refresh(refreshToken)));
  }

}
