package com.project.moru.user.controller;

import com.project.moru.common.utils.ApiResponse;
import com.project.moru.user.domain.dto.UserCreateRequestDto;
import com.project.moru.user.domain.dto.UserResponseDto;
import com.project.moru.user.domain.dto.UserUpdateRequestDto;
import com.project.moru.user.domain.entity.CustomUserDetails;
import com.project.moru.user.service.UserServiceImpl;
import io.swagger.v3.oas.annotations.Parameter;
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
  
  @PostMapping("/register")
  public ResponseEntity<ApiResponse<Void>> create(
      @Valid @RequestBody UserCreateRequestDto userCreateRequestDto
  ) {
    
    userService.create(userCreateRequestDto);
    
    return ResponseEntity.ok().body(ApiResponse.ok(201, "Created"));
  }
  
  @GetMapping("/me")
  public ResponseEntity<ApiResponse<UserResponseDto>> getUserProfile(
      @Parameter(hidden = true)
      @AuthenticationPrincipal CustomUserDetails userDetails
  ) {
    
    String username = userDetails.getUsername();
    
    return ResponseEntity.ok().body(ApiResponse.ok(userService.findByUsername(username)));
  }
  
  @PatchMapping("/me")
  public ResponseEntity<ApiResponse<UserResponseDto>> updateUserProfile(
      @Parameter(hidden = true)
      @AuthenticationPrincipal CustomUserDetails userDetails,
      @Valid @RequestBody UserUpdateRequestDto userUpdateRequestDto
      ) {
    
    Long id = userDetails.getId();
    
    return ResponseEntity.ok().body(ApiResponse.ok(userService.update(id, userUpdateRequestDto)));
  }
  
  @PatchMapping("/{id}/use-yn")
  public ResponseEntity<ApiResponse<Void>> convertActivate(@PathVariable Long id) {
    userService.toggleUserUseYn(id);
    return ResponseEntity.ok().body(ApiResponse.ok());
  }
  
  @DeleteMapping("/me")
  public ResponseEntity<ApiResponse<Void>> delete(
      @Parameter(hidden = true)
      @AuthenticationPrincipal CustomUserDetails userDetails) {
    userService.delete(userDetails.getId());
    return ResponseEntity.ok().body(ApiResponse.ok());
  }
}
