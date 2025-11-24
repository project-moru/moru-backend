package com.project.moru.service;

import com.project.moru.common.exception.ErrorCode;
import com.project.moru.common.exception.GeneralException;
import com.project.moru.domain.dto.card.CardCreateRequestDto;
import com.project.moru.domain.dto.card.CardResponseDto;
import com.project.moru.domain.dto.card.CardUpdateRequestDto;
import com.project.moru.domain.entity.card.Card;
import com.project.moru.domain.entity.user.User;
import com.project.moru.mapper.struct.CardConverter;
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

    private final CardRepository cardRepository;
    private final UserRepository userRepository;
    private final CardConverter cardConverter;
    @Override
    public CardResponseDto findById(Long id) {
        Card card = cardRepository.findById(id)
                .orElseThrow(() -> new GeneralException(ErrorCode.NOT_FOUND_CARD));
        return cardConverter.fromEntityToRes(card);
    }

    @Override
    public void deleteCardById(Long id, Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new GeneralException(ErrorCode.NOT_FOUND_USER));
        Card card = cardRepository.findById(id)
                .orElseThrow(() -> new GeneralException(ErrorCode.NOT_FOUND_CARD));

        if (!user.getUserId().equals(card.getUser().getUserId())) {
            throw new GeneralException(ErrorCode.ACCESS_DENIED);
        }
        cardRepository.deleteById(id);
    }

    @Override
    public CardResponseDto saveCard(CardCreateRequestDto cardCreateRequestDto,  Long userId) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new GeneralException(ErrorCode.NOT_FOUND_USER));

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

        return  cardConverter.fromEntityToRes(cardRepository.save(newCard));
    }

    @Override
    public CardResponseDto modifyCard(Long id, CardUpdateRequestDto cardUpdateRequestDto, Long userId) {

        Card card = cardRepository.findById(id)
                .orElseThrow(() -> new GeneralException(ErrorCode.NOT_FOUND_CARD));

        if (!card.getUser().getUserId().equals(userId)) {
            throw new GeneralException(ErrorCode.ACCESS_DENIED);
        }

        card.updateCard(
                cardUpdateRequestDto.getCardName(),
                cardUpdateRequestDto.getCardContent(),
                cardUpdateRequestDto.getIsPublic(),
                cardUpdateRequestDto.getImageUrl()
        );

        return cardConverter.fromEntityToRes(cardRepository.save(card));
    }

    @Override
    public List<CardResponseDto> findAll() {
        return cardConverter.toResList(cardRepository.findAll());
    }
}
