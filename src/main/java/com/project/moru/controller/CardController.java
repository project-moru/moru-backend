package com.project.moru.controller;

import com.project.moru.common.utils.ApiResponse;
import com.project.moru.domain.dto.card.CardCreateRequestDto;
import com.project.moru.domain.dto.card.CardUpdateRequestDto;
import com.project.moru.domain.entity.card.Card;
import com.project.moru.domain.entity.user.User;
import com.project.moru.service.CardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cards")
@RequiredArgsConstructor
public class CardController {
    
    private final CardService cardService;
    
    @PostMapping("/")
    public ResponseEntity<ApiResponse<Card>> create(@RequestBody CardCreateRequestDto cardCreateRequestDto, @AuthenticationPrincipal User user) {
        return ResponseEntity.ok().body(ApiResponse.ok(cardService.saveCard(cardCreateRequestDto, user.getUserId())));
    }
    
    @GetMapping("/")
    public ResponseEntity<ApiResponse<List<Card>>> getAll() {
        return ResponseEntity.ok().body(ApiResponse.ok(cardService.findAll()));
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Card>> getById(@PathVariable Long id) {
        return ResponseEntity.ok().body(ApiResponse.ok(cardService.findById(id)));
    }
    
    @PatchMapping("/{id}")
    public ResponseEntity<ApiResponse<Card>> update(@PathVariable Long id, @RequestBody CardUpdateRequestDto cardUpdateRequestDto, @AuthenticationPrincipal User user) {
        return ResponseEntity.ok().body(ApiResponse.ok(cardService.modifyCard(id,cardUpdateRequestDto, user.getUserId())));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> delete(@PathVariable Long id, @AuthenticationPrincipal User user) {
        cardService.deleteCardById(id, user.getUserId());
        return ResponseEntity.ok().body(ApiResponse.ok());
    }
}
