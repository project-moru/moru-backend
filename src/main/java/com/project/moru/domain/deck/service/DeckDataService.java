package com.project.moru.domain.deck.service;

import com.project.moru.domain.deck.entity.Deck;

import java.util.List;

public interface DeckDataService {
    Deck saveDeck(Deck deck,Long userId);
    List<Deck> findAllDecks(Long userId);
    Deck findDeckById(Long deckId);
    void  deleteDeckById(Long deckId, Long userId);
}
