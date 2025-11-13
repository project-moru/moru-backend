package com.project.moru.controller;

import com.project.moru.domain.entity.card.Card;
import com.project.moru.repository.CardRepository;
import com.project.moru.service.CardService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/card")
@RequiredArgsConstructor

public class CardController {
    private CardService cardService;

    @GetMapping("/")
    public ResponseEntity<ApiResponse>
}
