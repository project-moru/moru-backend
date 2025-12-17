package com.project.moru.cardlink.controller;

import com.project.moru.cardlink.domain.dto.CardLinkBlockCreateRequestDto;
import com.project.moru.cardlink.domain.dto.CardLinkBlockResponseDto;
import com.project.moru.cardlink.service.CardLinkService;
import com.project.moru.common.utils.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "card link-block", description = "카드 연결 블록 API")
@SecurityRequirement(name = "bearerAuth")
@RestController
@RequestMapping("/api/card-link")
@RequiredArgsConstructor
public class CardLinkBlockController {
  
  private final CardLinkService cardLinkService;
  
  @Operation(summary = "연결 블록 카드 추가 API")
  @PostMapping()
  public ResponseEntity<ApiResponse<CardLinkBlockResponseDto>> createCardLinkBlock(
      @RequestBody CardLinkBlockCreateRequestDto requestDto
  ) {
    return ResponseEntity.ok().body(ApiResponse.ok(cardLinkService.register(requestDto)));
  }
  
  @Operation(summary = "연결 블록 카드 삭제 API")
  @DeleteMapping("/{card_link_block_id}")
  public ResponseEntity<ApiResponse<Void>> deleteCardLinkBlock(
      @PathVariable Long card_link_block_id
  ) {
    cardLinkService.delete(card_link_block_id);
    return ResponseEntity.ok().body(ApiResponse.ok());
  }
}