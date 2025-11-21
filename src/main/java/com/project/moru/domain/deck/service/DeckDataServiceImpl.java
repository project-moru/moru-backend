package com.project.moru.domain.deck.service;

import com.project.moru.common.exception.ErrorCode;
import com.project.moru.common.exception.GeneralException;
import com.project.moru.domain.deck.entity.Deck;
import com.project.moru.domain.deck.repository.DeckRepository;
import com.project.moru.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class DeckDataServiceImpl implements DeckDataService {

    private final DeckRepository deckRepository;

    @Override
    public Deck saveDeck(Deck deck, Long userId) {
        return null;
    }

    @Override
    public List<Deck> findAllDecks(Long userId) {
        List<Deck> decks = deckRepository.findAllByUserId(userId);

        if (decks.isEmpty()) {
            throw new GeneralException(ErrorCode.NOT_FOUND_DECK);
        }

        return decks;
    }

    @Override
    public Deck findDeckById(Long deckId) {
        return deckRepository.findById(deckId).
                orElseThrow(() -> new GeneralException(ErrorCode.NOT_FOUND_DECK));
    }

    @Override
    public void deleteDeckById(Long deckId, Long userId) {

        Deck deck = deckRepository.findById(deckId).orElseThrow();

        if(deck.getUser().getUserId().equals(userId)){
            deckRepository.deleteById(deckId);
        } else {
            throw new GeneralException(ErrorCode.ACCESS_DENIED);
        }
    }

}
