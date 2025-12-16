package com.project.moru.deck.service_data;

import com.project.moru.common.exception.ErrorCode;
import com.project.moru.common.exception.GeneralException;
import com.project.moru.deck.mapper.DeckConverter;
import com.project.moru.deck.domain.dto.DeckRequestDto;
import com.project.moru.deck.domain.dto.DeckResponseDto;
import com.project.moru.deck.domain.entity.Deck;
import com.project.moru.deck.domain.entity.DeckCard;
import com.project.moru.deck.repository.DeckCardRepository;
import com.project.moru.deck.repository.DeckRepository;
import com.project.moru.card.domain.entity.Card;
import com.project.moru.user.domain.entity.User;
import com.project.moru.card.repository.CardRepository;
import com.project.moru.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DeckDataServiceImpl implements DeckDataService {

    private final DeckRepository deckRepository;
    private final CardRepository cardRepository;
    private final UserRepository userRepository;
    private final DeckCardRepository deckCardRepository;
    private final DeckConverter deckConverter;

    @Override
    public DeckResponseDto saveDeck(DeckRequestDto deckRequestDto, Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new GeneralException(ErrorCode.NOT_FOUND_USER));

        Deck deck = deckConverter.toEntity(deckRequestDto, user);

        Deck savedDeck = deckRepository.save(deck);

        return deckConverter.toDto(savedDeck);
    }

    @Override
    public List<DeckResponseDto> findAllDecks(Long userId) {
        List<Deck> decks = deckRepository.findAllByUser_Id(userId);

        if (decks.isEmpty()) {
            throw new GeneralException(ErrorCode.NOT_FOUND_DECK);
        }

        return decks.stream()
                .map(deckConverter::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public DeckResponseDto findDeckById(Long deckId,Long  userId) {
        Deck deck = deckRepository.findById(deckId)
                .orElseThrow(() -> new GeneralException(ErrorCode.NOT_FOUND_DECK));

        if(deck.getUser().getId().equals(userId)){
            return deckConverter.toDto(deck);
        } else {
            throw new GeneralException(ErrorCode.NOT_FOUND_DECK);
        }
    }

    @Override
    public void deleteDeckById(Long deckId, Long userId) {

        Deck deck = deckRepository.findById(deckId)
                .orElseThrow(() -> new GeneralException(ErrorCode.NOT_FOUND_DECK));

        if(deck.getUser().getId().equals(userId)){
            deckRepository.deleteById(deckId);
        } else {
            throw new GeneralException(ErrorCode.ACCESS_DENIED);
        }
    }

    @Override
    public DeckResponseDto saveCardToDeck(Long deckId, Long userId, ArrayList<Long> cardIds) {

        Deck deck = deckRepository.findById(deckId)
                .orElseThrow(() -> new GeneralException(ErrorCode.NOT_FOUND_DECK));

        if (!deck.getUser().getId().equals(userId)) {
            throw new GeneralException(ErrorCode.ACCESS_DENIED);
        }

        List<Card> cards = cardRepository.findAllById(cardIds);
        if (cards.size() != cardIds.size()) {
            throw new GeneralException(ErrorCode.NOT_EXIST_CARD);
        }

        for (Card card : cards) {
            DeckCard deckCard = DeckCard.builder()
                    .deck(deck)
                    .card(card)
                    .build();

            deck.addDeckCard(deckCard);

            deckRepository.save(deck);
        }
        return deckConverter.toDto(deck);
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
