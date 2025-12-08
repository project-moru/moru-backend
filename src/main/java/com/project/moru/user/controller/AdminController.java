package com.project.moru.user.controller;

import com.project.moru.common.utils.ApiResponse;
import com.project.moru.user.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "admin", description = "관리자 API")
@SecurityRequirement(name = "bearerAuth")
@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
public class AdminController {
  
  private final UserService userService;
  
  @Operation(summary = "사용자 활성화 API")
  @PatchMapping("/users/{id}/activation")
  public ResponseEntity<ApiResponse<Void>> convertActivate(@PathVariable Long id) {
    userService.toggleUserUseYn(id);
    return ResponseEntity.ok().body(ApiResponse.ok());
  }
}