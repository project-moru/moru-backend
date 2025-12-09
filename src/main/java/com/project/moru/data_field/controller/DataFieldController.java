package com.project.moru.data_field.controller;

import com.project.moru.common.utils.ApiResponse;
import com.project.moru.data_field.domain.dto.*;
import com.project.moru.data_field.service.AttributeService;
import com.project.moru.data_field.service.DataFieldService;
import com.project.moru.user.domain.entity.CustomUserDetails;
import com.project.moru.user.domain.entity.User;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "data field", description = "데이터 필드 API")
@SecurityRequirement(name = "bearerAuth")
@RestController
@RequestMapping("/api/data-field")
@RequiredArgsConstructor
public class DataFieldController {
  
  private final DataFieldService dataFieldService;
  private final AttributeService attributeService;
  
  @Operation(summary = "블록 생성 API")
  @PostMapping("/attribute")
  public ResponseEntity<ApiResponse<AttributeResponseDto>> createAttribute(
      @RequestBody AttributeCreateRequestDto requestDto,
      @Parameter(hidden = true) @AuthenticationPrincipal CustomUserDetails userDetails
      ) {
    return ResponseEntity.ok().body(ApiResponse.ok(attributeService.register(requestDto, userDetails.getId())));
  }
  
  @Operation(summary = "데이터 필드 생성 API")
  @PostMapping()
  public ResponseEntity<ApiResponse<DataFieldResponseDto>> createDataField(
      @RequestBody DataFieldCreateRequestDto requestDto,
      @Parameter(hidden = true) @AuthenticationPrincipal CustomUserDetails userDetails
      ) {
    return ResponseEntity.ok().body(ApiResponse.ok(dataFieldService.register(requestDto, userDetails.getId())));
  }
  
  @Operation(summary = "데이터 필드 목록 조회 API")
  @GetMapping()
  public ResponseEntity<ApiResponse<List<DataFieldResponseDto>>> getDataFieldsByUser(
      @Parameter(hidden = true) @AuthenticationPrincipal CustomUserDetails userDetails
  ) {
    return ResponseEntity.ok().body(ApiResponse.ok(dataFieldService.getListByUser(userDetails.getId())));
  }
  
  @Operation(summary = "데이터 필드 단일 조회 API")
  @GetMapping("/{data_field_id}")
  public ResponseEntity<ApiResponse<DataFieldDetailResponseDto>> getDataField(
      @PathVariable Long data_field_id,
      @Parameter(hidden = true) @AuthenticationPrincipal CustomUserDetails userDetails
  ) {
    return ResponseEntity.ok().body(ApiResponse.ok(dataFieldService.getDataFieldById(data_field_id, userDetails.getId())));
  }
  
  @Operation(summary = "데이터 필드 수정 API")
  @PatchMapping("/{data_field_id}")
  public ResponseEntity<ApiResponse<DataFieldResponseDto>> patchDataField(
      @PathVariable Long data_field_id,
      @RequestBody DataFieldUpdateRequestDto requestDto,
      @Parameter(hidden = true) @AuthenticationPrincipal CustomUserDetails userDetails
  ) {
    return ResponseEntity.ok().body(ApiResponse.ok(dataFieldService.update(data_field_id, requestDto, userDetails.getId())));
  }
  
  @Operation(summary = "데이터 필드 삭제 API")
  @DeleteMapping("/{data_field_id}")
  public ResponseEntity<ApiResponse<Void>> deleteDataField(
      @PathVariable Long data_field_id,
      @Parameter(hidden = true) @AuthenticationPrincipal CustomUserDetails userDetails
  ) {
    dataFieldService.delete(data_field_id, userDetails.getId());
    return ResponseEntity.ok().body(ApiResponse.ok());
  }
}
