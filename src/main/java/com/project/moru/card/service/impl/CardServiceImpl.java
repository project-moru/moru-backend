package com.project.moru.card.service.impl;

import com.project.moru.card.service.CardService;
import com.project.moru.common.constant.Status;
import com.project.moru.common.exception.ErrorCode;
import com.project.moru.common.exception.GeneralException;
import com.project.moru.card.domain.dto.CardCreateRequestDto;
import com.project.moru.card.domain.dto.CardResponseDto;
import com.project.moru.card.domain.dto.CardUpdateRequestDto;
import com.project.moru.card.domain.entity.Card;
import com.project.moru.common.utils.S3Utils;
import com.project.moru.data_field.domain.entity.DataField;
import com.project.moru.data_field.repository.DataFieldRepository;
import com.project.moru.user.domain.entity.User;
import com.project.moru.card.mapper.CardConverter;
import com.project.moru.card.repository.CardRepository;
import com.project.moru.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class CardServiceImpl implements CardService {

    private final CardRepository cardRepository;
    private final UserRepository userRepository;
    private final CardConverter cardConverter;
    private final S3Utils s3Utils;
    private final DataFieldRepository dataFieldRepository;

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


        s3Utils.deleteFile(card.getImageUrl());

        if (!user.getId().equals(card.getUser().getId())) {
            throw new GeneralException(ErrorCode.ACCESS_DENIED);
        }
        cardRepository.deleteById(id);
    }

    @Override
    public CardResponseDto saveCard(CardCreateRequestDto cardCreateRequestDto, Long userId, MultipartFile multipartFile) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new GeneralException(ErrorCode.NOT_FOUND_USER));

        Long dataFiledId = cardCreateRequestDto.getDataFieldId();
        DataField dataField = dataFieldRepository.findById(dataFiledId)
                .orElseThrow(() -> new GeneralException(ErrorCode.NOT_FOUND_DATA_FIELD));

        Card newCard = Card.builder()
                .user(user)
                .dataField(dataField)
                .status(cardCreateRequestDto.getStatus())
                .imageUrl(s3Utils.uploadFile("cards", multipartFile))
                .cardName(cardCreateRequestDto.getCardName())
                .tagCount(0)
                .viewCount(0)
                .likeCount(0)
                .build();

        return  cardConverter.fromEntityToRes(cardRepository.save(newCard));
    }

    @Override
    @Transactional
    public CardResponseDto modifyCard(Long id, CardUpdateRequestDto cardUpdateRequestDto, Long userId, MultipartFile multipartFile) {

        Card card = cardRepository.findById(id)
                .orElseThrow(() -> new GeneralException(ErrorCode.NOT_FOUND_CARD));

        if (!card.getUser().getId().equals(userId)) {
            throw new GeneralException(ErrorCode.ACCESS_DENIED);
        }

        DataField newDataField = null;
        if (cardUpdateRequestDto.getDataFiledId() != null) {
            newDataField = dataFieldRepository.findById(cardUpdateRequestDto.getDataFiledId())
                    .orElseThrow(() -> new GeneralException(ErrorCode.NOT_FOUND_DATA_FIELD));
        }

        String newImageUrl = card.getImageUrl();
        if (multipartFile != null && !multipartFile.isEmpty()) {
            if (card.getImageUrl() != null) {
                s3Utils.deleteFile(card.getImageUrl());
            }
            newImageUrl = s3Utils.uploadFile("cards", multipartFile);
        }

        card.updateCard(
                cardUpdateRequestDto.getCardName(),
                cardUpdateRequestDto.getStatus(),
                newDataField,
                newImageUrl
        );

        return cardConverter.fromEntityToRes(card);
    }

    @Override
    public List<CardResponseDto> findAll(Long userId) {
        return cardConverter.toResList(cardRepository.findAllByUser_IdOrStatus(userId,Status.PUBLIC));
    }

    @Override
    public List<CardResponseDto> findAllMyCards() {
        return cardConverter.toResList(cardRepository.findAllByStatus(Status.PUBLIC));
    }

//    @Override
//    public void toggleLike(Long cardId, Long userId) {
//
//    }
}
