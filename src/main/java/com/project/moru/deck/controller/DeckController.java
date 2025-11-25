package com.project.moru.deck.controller;

import com.project.moru.common.utils.ApiResponse;
import com.project.moru.deck.service_data.DeckDataService;
import com.project.moru.deck.domain.dto.DeckRequestDto;
import com.project.moru.deck.domain.dto.DeckResponseDto;
import com.project.moru.user.domain.entity.User;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "deck", description = "덱 API")
@RestController
@RequestMapping("/api/deck")
@RequiredArgsConstructor
public class DeckController {

    private final DeckDataService deckDataService;

    @Operation(summary = "덱 전체 조회")
    @GetMapping("/")
    public ResponseEntity<ApiResponse<List<DeckResponseDto>>> findAll(
            @Parameter(hidden = true) @AuthenticationPrincipal User user
    ) {
        return ResponseEntity.ok().body(ApiResponse.ok(deckDataService.findAllDecks(user.getId())));
    }

    @Operation(summary = "덱 단일 조회")
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<DeckResponseDto>> findById(
            @PathVariable Long id,
            @Parameter(hidden = true) @AuthenticationPrincipal User user
    ) {
        return ResponseEntity.ok().body(ApiResponse.ok(deckDataService.findDeckById(id, user.getId())));
    }

    @Operation(summary = "덱 생성")
    @PostMapping("/")
    public ResponseEntity<ApiResponse<DeckResponseDto>> save(
            @RequestBody DeckRequestDto deckRequestDto,
            @Parameter(hidden = true) @AuthenticationPrincipal User user)
    {
        return ResponseEntity.ok().body(ApiResponse.ok(deckDataService.saveDeck(deckRequestDto, user.getId())));
    }

    @Operation(summary = "덱 삭제")
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> delete(
            @PathVariable Long id,
            @Parameter(hidden = true) @AuthenticationPrincipal User user
    ) {
        deckDataService.deleteDeckById(id, user.getId());
        return ResponseEntity.ok().body(ApiResponse.ok());
    }
}
