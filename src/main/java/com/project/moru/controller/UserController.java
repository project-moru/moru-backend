package com.project.moru.controller;

import com.project.moru.common.utils.ApiResponse;
import com.project.moru.domain.dto.user.UserCreateRequestDto;
import com.project.moru.domain.dto.user.UserResponseDto;
import com.project.moru.domain.dto.user.UserUpdateRequestDto;
import com.project.moru.service.impl.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {
  
  private final UserServiceImpl userService;
  
  @PostMapping("/")
  public ResponseEntity<ApiResponse<UserResponseDto>> create(@RequestBody UserCreateRequestDto userCreateRequestDto) {
    return ResponseEntity.ok().body(ApiResponse.ok(userService.create(userCreateRequestDto)));
  }
  
  @GetMapping("/")
  public ResponseEntity<ApiResponse<List<UserResponseDto>>> getAll() {
    return ResponseEntity.ok().body(ApiResponse.ok(userService.findAll()));
  }
  
  @GetMapping("/{id}")
  public ResponseEntity<ApiResponse<UserResponseDto>> getById(@PathVariable Long id) {
    return ResponseEntity.ok().body(ApiResponse.ok(userService.findById(id)));
  }
  
  @PatchMapping("/{id}")
  public ResponseEntity<ApiResponse<UserResponseDto>> update(
      @PathVariable Long id,
      @RequestBody UserUpdateRequestDto userUpdateRequestDto
      ) {
    return ResponseEntity.ok().body(ApiResponse.ok(userService.update(id, userUpdateRequestDto)));
  }
  
  @PatchMapping("/{id}/use-yn")
  public ResponseEntity<ApiResponse<Void>> convertActivate(@PathVariable Long id) {
    userService.toggleUserUseYn(id);
    return ResponseEntity.ok().body(ApiResponse.ok());
  }
  
  @DeleteMapping("/{id}")
  public ResponseEntity<ApiResponse<Void>> delete(@PathVariable Long id) {
    userService.delete(id);
    return ResponseEntity.ok().body(ApiResponse.ok());
  }
}
