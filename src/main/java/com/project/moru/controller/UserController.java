package com.project.moru.controller;

import com.project.moru.common.utils.ApiResponse;
import com.project.moru.domain.dto.user.UserCreateRequestDto;
import com.project.moru.domain.dto.user.UserResponseDto;
import com.project.moru.domain.dto.user.UserUpdateRequestDto;
import com.project.moru.domain.entity.user.CustomUserDetails;
import com.project.moru.service.impl.UserServiceImpl;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@SecurityRequirement(name = "bearerAuth")
@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {
  
  private final UserServiceImpl userService;
  
  @PostMapping()
  public ResponseEntity<ApiResponse<Void>> create(
      @Valid @RequestBody UserCreateRequestDto userCreateRequestDto
  ) {
    
    userService.create(userCreateRequestDto);
    
    return ResponseEntity.ok().body(ApiResponse.ok(201, "Created"));
  }
  
  @GetMapping("/me")
  public ResponseEntity<ApiResponse<UserResponseDto>> getUserProfile(
      @AuthenticationPrincipal CustomUserDetails userDetails
  ) {
    
    String username = userDetails.getUsername();
    
    return ResponseEntity.ok().body(ApiResponse.ok(userService.findByUsername(username)));
  }
  
  @PatchMapping("/me")
  public ResponseEntity<ApiResponse<UserResponseDto>> updateUserProfile(
      @AuthenticationPrincipal CustomUserDetails userDetails,
      @RequestBody UserUpdateRequestDto userUpdateRequestDto
      ) {
    
    Long id = userDetails.getUserId();
    
    return ResponseEntity.ok().body(ApiResponse.ok(userService.update(id, userUpdateRequestDto)));
  }
  
  @PatchMapping("/{id}/use-yn")
  public ResponseEntity<ApiResponse<Void>> convertActivate(@PathVariable Long id) {
    userService.toggleUserUseYn(id);
    return ResponseEntity.ok().body(ApiResponse.ok());
  }
  
  @DeleteMapping("/me")
  public ResponseEntity<ApiResponse<Void>> delete(@AuthenticationPrincipal CustomUserDetails userDetails) {
    userService.delete(userDetails.getUserId());
    return ResponseEntity.ok().body(ApiResponse.ok());
  }
}
