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
  
  @GetMapping("/")
  public ResponseEntity<ApiResponse<List<UserResponseDto>>> findAll() {
    return ResponseEntity.ok().body(ApiResponse.ok(userService.findAll()));
  }
  
  @GetMapping("/{id}")
  public ResponseEntity<ApiResponse<UserResponseDto>> findById(@PathVariable Long id) {
    return ResponseEntity.ok().body(ApiResponse.ok(userService.findById(id)));
  }
  
  @PostMapping("/")
  public ResponseEntity<ApiResponse<UserResponseDto>> save(@RequestBody UserCreateRequestDto userCreateRequestDto) {
    return ResponseEntity.ok().body(ApiResponse.ok(userService.save(userCreateRequestDto)));
  }
  
  @PatchMapping("/{id}")
  public ResponseEntity<ApiResponse<UserResponseDto>> modify(
      @PathVariable Long id,
      @RequestBody UserUpdateRequestDto userUpdateRequestDto
      ) {
    return ResponseEntity.ok().body(ApiResponse.ok(userService.modify(id, userUpdateRequestDto)));
  }
  
  @DeleteMapping("/{id}")
  public ResponseEntity<ApiResponse<Void>> delete(@PathVariable Long id) {
    userService.delete(id);
    return ResponseEntity.ok().body(ApiResponse.ok());
  }
}
