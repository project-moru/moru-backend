package com.project.moru.controller;

import com.project.moru.common.utils.ApiResponse;
import com.project.moru.domain.dto.card.CardCreateRequestDto;
import com.project.moru.domain.dto.card.CardUpdateRequestDto;
import com.project.moru.domain.entity.card.Card;
import com.project.moru.domain.entity.user.User;
import com.project.moru.service.CardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/card")
@RequiredArgsConstructor

public class CardController {
    private CardService cardService;

    @GetMapping("/")
    public ResponseEntity<ApiResponse<List<Card>>> findAll() {
        return ResponseEntity.ok().body(ApiResponse.ok(cardService.findAll()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Card>> findById(@PathVariable Long id) {
        return ResponseEntity.ok().body(ApiResponse.ok(cardService.findById(id)));
    }

    @PostMapping("/")
    public ResponseEntity<ApiResponse<Card>> save(@RequestBody CardCreateRequestDto cardCreateRequestDto, @AuthenticationPrincipal User user) {
        return ResponseEntity.ok().body(ApiResponse.ok(cardService.saveCard(cardCreateRequestDto, user.getUserId())));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> delete(@PathVariable Long id, @AuthenticationPrincipal User user) {
        cardService.deleteCardById(id, user.getUserId());
        return ResponseEntity.ok().body(ApiResponse.ok());
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ApiResponse<Card>> modify(@PathVariable Long id, @RequestBody CardUpdateRequestDto cardUpdateRequestDto, @AuthenticationPrincipal User user) {
        return ResponseEntity.ok().body(ApiResponse.ok(cardService.modifyCard(id,cardUpdateRequestDto, user.getUserId())));
    }
}
