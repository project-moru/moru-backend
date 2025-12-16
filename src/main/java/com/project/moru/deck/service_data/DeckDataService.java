package com.project.moru.deck.service_data;

import com.project.moru.deck.domain.dto.DeckRequestDto;
import com.project.moru.deck.domain.dto.DeckResponseDto;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

public interface DeckDataService {
    DeckResponseDto saveDeck(DeckRequestDto deckRequestDto, Long userId);
    List<DeckResponseDto> findAllDecks(Long userId);
    DeckResponseDto findDeckById(Long deckId, Long userId);
    void  deleteDeckById(Long deckId, Long userId);
    DeckResponseDto saveCardToDeck(Long deckId, Long userId, ArrayList<Long> cardIds);
}
