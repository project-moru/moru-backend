package com.project.moru.domain.deck.dataservice;

import com.project.moru.domain.deck.dto.DeckRequestDto;
import com.project.moru.domain.deck.dto.DeckResponseDto;
import com.project.moru.domain.deck.entity.Deck;

import java.util.List;

public interface DeckDataService {
    DeckResponseDto saveDeck(DeckRequestDto deckRequestDto, Long userId);
    List<DeckResponseDto> findAllDecks(Long userId);
    DeckResponseDto findDeckById(Long deckId, Long userId);
    void  deleteDeckById(Long deckId, Long userId);
}
