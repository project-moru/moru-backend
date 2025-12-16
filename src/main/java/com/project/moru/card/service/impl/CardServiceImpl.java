package com.project.moru.card.service.impl;

import com.project.moru.card.service.CardService;
import com.project.moru.common.constant.Status;
import com.project.moru.common.exception.ErrorCode;
import com.project.moru.common.exception.GeneralException;
import com.project.moru.card.domain.dto.CardCreateRequestDto;
import com.project.moru.card.domain.dto.CardResponseDto;
import com.project.moru.card.domain.dto.CardUpdateRequestDto;
import com.project.moru.card.domain.entity.Card;
import com.project.moru.user.domain.entity.User;
import com.project.moru.card.mapper.CardConverter;
import com.project.moru.card.repository.CardRepository;
import com.project.moru.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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
    public CardResponseDto findById(Long id, Long userId) {
        Card card = cardRepository.findById(id)
                .orElseThrow(() -> new GeneralException(ErrorCode.NOT_FOUND_CARD));

        if(!card.getStatus().equals(Status.PUBLIC)){
            if (card.getUser().getId().equals(userId)) {
                cardConverter.fromEntityToRes(card);
            }
            else {
                throw new GeneralException(ErrorCode.NOT_FOUND_CARD);
            }
        }
        card.addViewCount();
        cardRepository.save(card);
        return cardConverter.fromEntityToRes(card);
    }

    @Override
    public void deleteCardById(Long id, Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new GeneralException(ErrorCode.NOT_FOUND_USER));
        Card card = cardRepository.findById(id)
                .orElseThrow(() -> new GeneralException(ErrorCode.NOT_FOUND_CARD));

        if (!user.getId().equals(card.getUser().getId())) {
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
                .status(cardCreateRequestDto.getStatus())
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

        if (!card.getUser().getId().equals(userId)) {
            throw new GeneralException(ErrorCode.ACCESS_DENIED);
        }

        card.updateCard(
                cardUpdateRequestDto.getCardName(),
                cardUpdateRequestDto.getCardContent(),
                cardUpdateRequestDto.getStatus(),
                cardUpdateRequestDto.getImageUrl()
        );

        return cardConverter.fromEntityToRes(cardRepository.save(card));
    }

    @Override
    public List<CardResponseDto> findAll(Long userId) {
        return cardConverter.toResList(cardRepository.findAllByUser_IdOrStatus(userId,Status.PUBLIC));
    }
}
