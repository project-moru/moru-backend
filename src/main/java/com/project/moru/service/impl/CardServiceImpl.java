package com.project.moru.service.impl;

import com.project.moru.domain.dto.card.CardCreateRequestDto;
import com.project.moru.domain.dto.card.CardUpdateRequestDto;
import com.project.moru.domain.entity.card.Card;
import com.project.moru.service.CardService;
import com.project.moru.domain.entity.user.User;
import com.project.moru.repository.CardRepository;
import com.project.moru.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class CardServiceImpl implements CardService {

    private CardRepository cardRepository;
    private UserRepository userRepository;

    @Override
    public Card findById(Long id) {
        return cardRepository.findById(id).orElse(null);
    }

    @Override
    public void deleteCardById(Long id, Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));
        Card card = cardRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Card not found"));

        if (!user.getUserId().equals(card.getUser().getUserId())) {
            throw new EntityNotFoundException("User not allowed to delete card");
        }
        cardRepository.deleteById(id);
    }

    @Override
    public Card saveCard(CardCreateRequestDto cardCreateRequestDto,  Long userId) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));

        Card newCard = Card.builder()
                .user(user)
                .cardContent(cardCreateRequestDto.getCardContent())
                .imageUrl(cardCreateRequestDto.getImageUrl())
                .isPublic(cardCreateRequestDto.getIsPublic())
                .imageUrl(cardCreateRequestDto.getImageUrl())
                .cardName(cardCreateRequestDto.getCardName())
                .tagCount(0)
                .viewCount(0)
                .likeCount(0)
                .build();
        return  cardRepository.save(newCard);
    }

    @Override
    public Card modifyCard(Long id, CardUpdateRequestDto cardUpdateRequestDto, Long userId) {

        Card card = cardRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Card not found"));

        if (!card.getUser().getUserId().equals(userId)) {
            // 권한이 없다면 예외 발생 (예: IllegalStateException, AccessDeniedException 등)
            throw new AccessDeniedException("You do not have permission to modify this card.");
        }

        card.updateCard(
                cardUpdateRequestDto.getCardName(),
                cardUpdateRequestDto.getCardContent(),
                cardUpdateRequestDto.getIsPublic(),
                cardUpdateRequestDto.getImageUrl()
        );

        return cardRepository.save(card);
    }

    @Override
    public List<Card> findAll() {
        cardRepository.findAll();
        return cardRepository.findAll();
    }
}
