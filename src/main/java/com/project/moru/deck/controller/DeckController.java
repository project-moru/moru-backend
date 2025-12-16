package com.project.moru.deck.controller;

import com.project.moru.common.utils.ApiResponse;
import com.project.moru.deck.service_data.DeckDataService;
import com.project.moru.deck.domain.dto.DeckRequestDto;
import com.project.moru.deck.domain.dto.DeckResponseDto;
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

import java.util.ArrayList;
import java.util.List;

@Tag(name = "deck", description = "덱 API")
@RestController
@SecurityRequirement(name = "bearerAuth")
@RequestMapping("/api/deck")
@RequiredArgsConstructor
public class DeckController {

    private final DeckDataService deckDataService;

    @Operation(summary = "덱 전체 조회")
    @GetMapping("")
    public ResponseEntity<ApiResponse<List<DeckResponseDto>>> findAll(
            @Parameter(hidden = true) @AuthenticationPrincipal CustomUserDetails userDetails
    ) {
        return ResponseEntity.ok().body(ApiResponse.ok(deckDataService.findAllDecks(userDetails.getId())));
    }

    @Operation(summary = "덱 단일 조회")
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<DeckResponseDto>> findById(
            @PathVariable Long id,
            @Parameter(hidden = true) @AuthenticationPrincipal CustomUserDetails userDetails
    ) {
        return ResponseEntity.ok().body(ApiResponse.ok(deckDataService.findDeckById(id, userDetails.getId())));
    }

    @Operation(summary = "덱 생성")
    @PostMapping("")
    public ResponseEntity<ApiResponse<DeckResponseDto>> save(
            @RequestBody DeckRequestDto deckRequestDto,
            @Parameter(hidden = true) @AuthenticationPrincipal CustomUserDetails userDetails
    ) {
        return ResponseEntity.ok().body(ApiResponse.ok(deckDataService.saveDeck(deckRequestDto, userDetails.getId())));
    }

    @Operation(summary = "덱 삭제")
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> delete(
            @PathVariable Long id,
            @Parameter(hidden = true) @AuthenticationPrincipal CustomUserDetails userDetails
    ) {
        deckDataService.deleteDeckById(id, userDetails.getId());
        return ResponseEntity.ok().body(ApiResponse.ok(200,"삭제에 성공하였습니다."));
    }

    @Operation(summary = "덱에 카드 추가")
    @PostMapping("/{deckId}/cards")
    public ResponseEntity<ApiResponse<DeckResponseDto>> cardSave(
        @RequestBody ArrayList<Long> cardIds,
        @PathVariable Long deckId,
        @Parameter(hidden = true) @AuthenticationPrincipal CustomUserDetails userDetails
    ) {
        return ResponseEntity.ok().body(ApiResponse.ok(deckDataService.saveCardToDeck(deckId, userDetails.getId(), cardIds)));
    }

    @Operation(summary = "덱에서 카드 제거")
    @DeleteMapping("/{deckId}/cards")
    public ResponseEntity<ApiResponse<DeckResponseDto>> removeCards(
            @PathVariable Long deckId,
            @RequestBody ArrayList<Long> cardIds,
            @Parameter(hidden = true) @AuthenticationPrincipal CustomUserDetails userDetails
    ) {
        return ResponseEntity.ok()
                .body(ApiResponse.ok(deckDataService.removeCardFromDeck(deckId, userDetails.getId(), cardIds)));
    }
}
