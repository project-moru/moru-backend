package com.project.moru.controller;

import com.project.moru.common.utils.ApiResponse;
import com.project.moru.domain.dto.card.CardCreateRequestDto;
import com.project.moru.domain.dto.card.CardResponseDto;
import com.project.moru.domain.dto.card.CardUpdateRequestDto;
import com.project.moru.domain.entity.user.User;
import com.project.moru.service.CardService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "card", description = "카드 API")
@RestController
@RequestMapping("/api/card")
@RequiredArgsConstructor
public class CardController {

    private final CardService cardService;

    @GetMapping("/")
    public ResponseEntity<ApiResponse<List<CardResponseDto>>> findAll() {
        return ResponseEntity.ok().body(ApiResponse.ok(cardService.findAll()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<CardResponseDto>> findById(@PathVariable Long id) {
        return ResponseEntity.ok().body(ApiResponse.ok(cardService.findById(id)));
    }

    @PostMapping("/")
    public ResponseEntity<ApiResponse<CardResponseDto>> save(@RequestBody CardCreateRequestDto cardCreateRequestDto, @AuthenticationPrincipal User user) {
        return ResponseEntity.ok().body(ApiResponse.ok(cardService.saveCard(cardCreateRequestDto, user.getUserId())));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> delete(@PathVariable Long id, @AuthenticationPrincipal User user) {
        cardService.deleteCardById(id, user.getUserId());
        return ResponseEntity.ok().body(ApiResponse.ok());
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ApiResponse<CardResponseDto>> modify(@PathVariable Long id, @RequestBody CardUpdateRequestDto cardUpdateRequestDto, @AuthenticationPrincipal User user) {
        return ResponseEntity.ok().body(ApiResponse.ok(cardService.modifyCard(id,cardUpdateRequestDto, user.getUserId())));
    }
}
