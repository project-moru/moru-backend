package com.project.moru.domain.deck.dataservice;

import com.project.moru.common.exception.ErrorCode;
import com.project.moru.common.exception.GeneralException;
import com.project.moru.domain.deck.DeckMapper;
import com.project.moru.domain.deck.dto.DeckRequestDto;
import com.project.moru.domain.deck.dto.DeckResponseDto;
import com.project.moru.domain.deck.entity.Deck;
import com.project.moru.domain.deck.entity.DeckCard;
import com.project.moru.domain.deck.repository.DeckCardRepository;
import com.project.moru.domain.deck.repository.DeckRepository;
import com.project.moru.domain.entity.card.Card;
import com.project.moru.domain.entity.user.User;
import com.project.moru.repository.CardRepository;
import com.project.moru.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DeckDataServiceImpl implements DeckDataService {

    private final DeckRepository deckRepository;
    private final CardRepository cardRepository;
    private final UserRepository userRepository;
    private final DeckCardRepository deckCardRepository;
    private final DeckMapper deckMapper;

    @Override
    public DeckResponseDto saveDeck(DeckRequestDto deckRequestDto, Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new GeneralException(ErrorCode.NOT_FOUND_USER));

        Deck deck = deckMapper.toEntity(deckRequestDto, user);

        Deck savedDeck = deckRepository.save(deck);

        if(!deckRequestDto.getCardIds().isEmpty()){
            linkCardsToDeck(savedDeck,deckRequestDto.getCardIds());
        }

        return deckMapper.toDto(savedDeck);
    }

    @Override
    public List<DeckResponseDto> findAllDecks(Long userId) {
        List<Deck> decks = deckRepository.findAllByUser_UserId(userId);

        if (decks.isEmpty()) {
            throw new GeneralException(ErrorCode.NOT_FOUND_DECK);
        }

        return decks.stream()
                .map(deckMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public DeckResponseDto findDeckById(Long deckId,Long  userId) {
        Deck deck = deckRepository.findById(deckId)
                .orElseThrow(() -> new GeneralException(ErrorCode.NOT_FOUND_DECK));

        if(deck.getUser().getUserId().equals(userId)){
            return deckMapper.toDto(deck);
        } else {
            throw new GeneralException(ErrorCode.NOT_FOUND_DECK);
        }
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

    private void linkCardsToDeck(Deck deck, List<Long> cardIds) {
        List<Card> cards = cardRepository.findAllById(cardIds);
        if (cards.size() != cardIds.size()) {
            throw new GeneralException(ErrorCode.NOT_FOUND_CARD);
        }

        List<DeckCard> deckCards = cards.stream()
                .map(card -> DeckCard.builder()
                        .deck(deck)
                        .card(card)
                        .build())
                .collect(Collectors.toList());
        deckCardRepository.saveAll(deckCards);
    }

}
