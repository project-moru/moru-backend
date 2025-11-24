package com.project.moru.domain.deck.controller;

import com.project.moru.common.utils.ApiResponse;
import com.project.moru.domain.deck.dataservice.DeckDataService;
import com.project.moru.domain.deck.dto.DeckRequestDto;
import com.project.moru.domain.deck.dto.DeckResponseDto;
import com.project.moru.domain.entity.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/deck")
@RequiredArgsConstructor
public class DeckController {

    private final DeckDataService deckDataService;

    @GetMapping("/")
    public ResponseEntity<ApiResponse<List<DeckResponseDto>>> findAll(@AuthenticationPrincipal User user) {
        return ResponseEntity.ok().body(ApiResponse.ok(deckDataService.findAllDecks(user.getUserId())));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<DeckResponseDto>> findById(@PathVariable Long id, @AuthenticationPrincipal User user) {
        return ResponseEntity.ok().body(ApiResponse.ok(deckDataService.findDeckById(id, user.getUserId())));
    }

    @PostMapping("/")
    public ResponseEntity<ApiResponse<DeckResponseDto>> save(@RequestBody DeckRequestDto deckRequestDto, @AuthenticationPrincipal User user) {
        return ResponseEntity.ok().body(ApiResponse.ok(deckDataService.saveDeck(deckRequestDto, user.getUserId())));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> delete(@PathVariable Long id, @AuthenticationPrincipal User user) {
        deckDataService.deleteDeckById(id, user.getUserId());
        return ResponseEntity.ok().body(ApiResponse.ok());
    }
}
