package com.project.moru.card.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.moru.card.domain.dto.CardCreateRequestDto;
import com.project.moru.card.domain.dto.CardResponseDto;
import com.project.moru.card.domain.dto.CardUpdateRequestDto;
import com.project.moru.card.service.CardLikeService;
import com.project.moru.card.service.CardService;
import com.project.moru.common.utils.ApiResponse;
import com.project.moru.user.domain.entity.CustomUserDetails;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Tag(name = "card", description = "카드 API")
@SecurityRequirement(name = "bearerAuth")
@RestController
@RequestMapping("/api/card")
@RequiredArgsConstructor
public class CardController {

    private final CardService cardService;
    private final CardLikeService cardLikeService;
    private final ObjectMapper objectMapper;

    @GetMapping("")
    @Operation(summary = "전체 카드 전체 조회")
    public ResponseEntity<ApiResponse<List<CardResponseDto>>> findAllMyCards(
    ) {
        return ResponseEntity.ok().body(ApiResponse.ok(cardService.findAllMyCards()));
    }

    @GetMapping("/my")
    @Operation(summary = "내 카드 조회")
    public ResponseEntity<ApiResponse<List<CardResponseDto>>> findAll(
            @Parameter(hidden = true) @AuthenticationPrincipal CustomUserDetails userDetails
    ) {
        return ResponseEntity.ok().body(ApiResponse.ok(cardService.findAll(userDetails.getId())));
    }

    @GetMapping("/{id}")
    @Operation(summary = "카드 단일 조회")
    public ResponseEntity<ApiResponse<CardResponseDto>> findById(
            @PathVariable Long id,
            @Parameter(hidden = true) @AuthenticationPrincipal CustomUserDetails userDetails
    ) {
        return ResponseEntity.ok().body(ApiResponse.ok(cardService.findById(id, userDetails.getId())));
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "카드 생성")
    public ResponseEntity<ApiResponse<CardResponseDto>> save(
            @Parameter(description = "카드 정보", schema = @Schema(implementation = CardCreateRequestDto.class))
            @RequestPart("card") String cardJson,

            @RequestPart("multipartFile") MultipartFile cardImage,
            @Parameter(hidden = true) @AuthenticationPrincipal CustomUserDetails userDetails
    ) throws JsonProcessingException {
        CardCreateRequestDto cardCreateRequestDto = objectMapper.readValue(cardJson, CardCreateRequestDto.class);

        return ResponseEntity.ok().body(ApiResponse.ok(cardService.saveCard(cardCreateRequestDto, userDetails.getId(), cardImage)));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "카드 삭제")
    public ResponseEntity<ApiResponse<Void>> delete(
            @PathVariable Long id,
            @Parameter(hidden = true) @AuthenticationPrincipal CustomUserDetails userDetails
    ) {
        cardService.deleteCardById(id, userDetails.getId());
        return ResponseEntity.ok().body(ApiResponse.ok(200,"삭제에 성공하였습니다."));
    }

    @PatchMapping(value = "{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "카드 수정")
    public ResponseEntity<ApiResponse<CardResponseDto>> modify(
            @PathVariable Long id,
            CardUpdateRequestDto cardUpdateRequestDto,
            @RequestPart("multipartFile") MultipartFile cardImage,
            @Parameter(hidden = true) @AuthenticationPrincipal CustomUserDetails userDetails
    ) {
        return ResponseEntity.ok().body(ApiResponse.ok(cardService.modifyCard(id,cardUpdateRequestDto, userDetails.getId(),cardImage)));
    }

    @PostMapping("/{cardId}/like")
    @Operation(summary = "카드 좋아요")
    public ResponseEntity<ApiResponse<CardResponseDto>> toggleLike(
            @PathVariable Long cardId,
            @Parameter(hidden = true) @AuthenticationPrincipal CustomUserDetails userDetails
    ) {
        return ResponseEntity.ok().body(ApiResponse.ok(cardLikeService.toggleLike(cardId, userDetails.getId())));
    }

    @GetMapping("/{cardId}/like-count")
    @Operation(summary = "실시간 좋아요 개수 조회")
    public ResponseEntity<ApiResponse<Long>> getLikeCount(@PathVariable Long cardId) {
        return ResponseEntity.ok().body(ApiResponse.ok(cardLikeService.getLikeCount(cardId)));
    }
}
