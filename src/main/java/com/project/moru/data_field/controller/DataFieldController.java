package com.project.moru.data_field.controller;

import com.project.moru.common.utils.ApiResponse;
import com.project.moru.data_field.domain.dto.*;
import com.project.moru.data_field.service.AttributeBlockService;
import com.project.moru.data_field.service.DataFieldService;
import com.project.moru.data_field.service.LinkBlockService;
import com.project.moru.user.domain.entity.CustomUserDetails;
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
  private final AttributeBlockService attributeBlockService;
  private final LinkBlockService linkBlockService;
  
  @Operation(summary = "데이터 필드 생성 API")
  @PostMapping()
  public ResponseEntity<ApiResponse<DataFieldResponseDto>> createDataField(
      @RequestBody DataFieldCreateRequestDto requestDto,
      @Parameter(hidden = true) @AuthenticationPrincipal CustomUserDetails userDetails
  ) {
    return ResponseEntity.ok().body(ApiResponse.ok(dataFieldService.register(requestDto, userDetails.getId())));
  }
  
  @Operation(summary = "속성 블록 생성 API")
  @PostMapping("/attribute")
  public ResponseEntity<ApiResponse<AttributeResponseDto>> createAttributeBlock(
      @RequestBody AttributeCreateRequestDto requestDto,
      @Parameter(hidden = true) @AuthenticationPrincipal CustomUserDetails userDetails
      ) {
    return ResponseEntity.ok().body(ApiResponse.ok(attributeBlockService.register(requestDto, userDetails.getId())));
  }
  
  @Operation(summary = "연결 블록 생성 API")
  @PostMapping("/link")
  public ResponseEntity<ApiResponse<LinkResponseDto>> createLinkBlock(
      @RequestBody LinkCreateRequestDto requestDto,
      @Parameter(hidden = true) @AuthenticationPrincipal CustomUserDetails userDetails
  ) {
    return ResponseEntity.ok().body(ApiResponse.ok(linkBlockService.register(requestDto, userDetails.getId())));
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
  
  @Operation(summary = "속성 블록 수정 API")
  @PatchMapping("/attribute/{id}")
  public ResponseEntity<ApiResponse<AttributeResponseDto>> patchAttributeBlock(
      @PathVariable Long id,
      @RequestBody AttributeUpdateRequestDto requestDto
  ) {
    return ResponseEntity.ok().body(ApiResponse.ok(attributeBlockService.update(id, requestDto)));
  }
  
  @Operation(summary = "연결 블록 수정 API")
  @PatchMapping("/link/{id}")
  public ResponseEntity<ApiResponse<LinkResponseDto>> patchLinkBlock(
      @PathVariable Long id,
      @RequestBody LinkUpdateRequestDto requestDto
  ) {
    return ResponseEntity.ok().body(ApiResponse.ok(linkBlockService.update(id, requestDto)));
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
  
  @Operation(summary = "속성 블록 삭제 API")
  @DeleteMapping("/attribute/{id}")
  public ResponseEntity<ApiResponse<Void>> deleteAttributeBlock(
      @PathVariable Long id
  ) {
    attributeBlockService.delete(id);
    return ResponseEntity.ok().body(ApiResponse.ok());
  }
  
  @Operation(summary = "연결 블록 삭제 API")
  @DeleteMapping("/link/{id}")
  public ResponseEntity<ApiResponse<Void>> deleteLinkBlock(
      @PathVariable Long id
  ) {
    linkBlockService.delete(id);
    return ResponseEntity.ok().body(ApiResponse.ok());
  }
}