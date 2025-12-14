package com.project.moru.card.controller;

import com.project.moru.common.utils.ApiResponse;
import com.project.moru.card.domain.dto.CardCreateRequestDto;
import com.project.moru.card.domain.dto.CardResponseDto;
import com.project.moru.card.domain.dto.CardUpdateRequestDto;
import com.project.moru.user.domain.entity.CustomUserDetails;
import com.project.moru.user.domain.entity.User;
import com.project.moru.card.service.CardService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "card", description = "카드 API")
@SecurityRequirement(name = "bearerAuth")
@RestController
@RequestMapping("/api/card")
@RequiredArgsConstructor
public class CardController {

    private final CardService cardService;

    @GetMapping("")
    @Operation(summary = "카드 전체 조회")
    public ResponseEntity<ApiResponse<List<CardResponseDto>>> findAll() {
        return ResponseEntity.ok().body(ApiResponse.ok(cardService.findAll()));
    }

    @GetMapping("/{id}")
    @Operation(summary = "카드 단일 조회")
    public ResponseEntity<ApiResponse<CardResponseDto>> findById(@PathVariable Long id) {
        return ResponseEntity.ok().body(ApiResponse.ok(cardService.findById(id)));
    }

    @PostMapping("")
    @Operation(summary = "카드 저장")
    public ResponseEntity<ApiResponse<CardResponseDto>> save(
            @RequestBody CardCreateRequestDto cardCreateRequestDto,
            @Parameter(hidden = true) @AuthenticationPrincipal CustomUserDetails userDetails
    ) {
        return ResponseEntity.ok().body(ApiResponse.ok(cardService.saveCard(cardCreateRequestDto, userDetails.getId())));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "카드 삭제")
    public ResponseEntity<ApiResponse<Void>> delete(
            @PathVariable Long id,
            @Parameter(hidden = true) @AuthenticationPrincipal CustomUserDetails userDetails
    ) {
        cardService.deleteCardById(id, userDetails.getId());
        return ResponseEntity.ok().body(ApiResponse.ok());
    }

    @PatchMapping("/{id}")
    @Operation(summary = "카드 수정")
    public ResponseEntity<ApiResponse<CardResponseDto>> modify(
            @PathVariable Long id,
            @RequestBody CardUpdateRequestDto cardUpdateRequestDto,
            @Parameter(hidden = true) @AuthenticationPrincipal CustomUserDetails userDetails
    ) {
        return ResponseEntity.ok().body(ApiResponse.ok(cardService.modifyCard(id,cardUpdateRequestDto, userDetails.getId())));
    }
}
