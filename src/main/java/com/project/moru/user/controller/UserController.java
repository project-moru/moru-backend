package com.project.moru.user.controller;

import com.project.moru.common.utils.ApiResponse;
import com.project.moru.user.domain.dto.UserCreateRequestDto;
import com.project.moru.user.domain.dto.UserResponseDto;
import com.project.moru.user.domain.dto.UserUpdateRequestDto;
import com.project.moru.user.domain.entity.CustomUserDetails;
import com.project.moru.user.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Tag(name = "user", description = "사용자 API")
@SecurityRequirement(name = "bearerAuth")
@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {
  
  private final UserService userService;
  
  @Operation(summary = "사용자 정보 조회 API")
  @GetMapping("/me")
  public ResponseEntity<ApiResponse<UserResponseDto>> getUserProfile(
      @Parameter(hidden = true)
      @AuthenticationPrincipal CustomUserDetails userDetails
  ) {
    
    String username = userDetails.getUsername();
    
    return ResponseEntity.ok().body(ApiResponse.ok(userService.findByUsername(username)));
  }
  
  @Operation(summary = "사용자 정보 수정 API")
  @PatchMapping("/me")
  public ResponseEntity<ApiResponse<UserResponseDto>> updateUserProfile(
      @Parameter(hidden = true)
      @AuthenticationPrincipal CustomUserDetails userDetails,
      @Valid @RequestBody UserUpdateRequestDto userUpdateRequestDto
      ) {
    
    Long id = userDetails.getId();
    
    return ResponseEntity.ok().body(ApiResponse.ok(userService.update(id, userUpdateRequestDto)));
  }
  
  @Operation(summary = "사용자 정보 삭제 API")
  @DeleteMapping("/me")
  public ResponseEntity<ApiResponse<Void>> delete(
      @Parameter(hidden = true)
      @AuthenticationPrincipal CustomUserDetails userDetails) {
    userService.delete(userDetails.getId());
    return ResponseEntity.ok().body(ApiResponse.ok());
  }
}
