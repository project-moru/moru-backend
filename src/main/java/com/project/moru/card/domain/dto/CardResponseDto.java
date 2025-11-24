package com.project.moru.card.domain.dto;

import com.project.moru.card.domain.entity.Card;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class CardResponseDto {
    private final Long cardId;
    private final String cardName;
    private final String cardContent;
    private final String imageUrl;
    private final Boolean isPublic;
    private final Integer tagCount;
    private final Integer viewCount;
    private final Integer likeCount;
    private final Long userId;
    private final String userNickname;
    private final LocalDateTime createdAt;
    private final LocalDateTime updatedAt;

    public static CardResponseDto fromEntity(Card card) {
        return CardResponseDto.builder()
                .cardId(card.getCardId())
                .cardName(card.getCardName())
                .cardContent(card.getCardContent())
                .imageUrl(card.getImageUrl())
                .isPublic(card.getIsPublic())
                .tagCount(card.getTagCount())
                .viewCount(card.getViewCount())
                .likeCount(card.getLikeCount())
                .userId(card.getUser().getUserId())
                .createdAt(card.getCreatedAt())
                .updatedAt(card.getModifiedAt())
                .build();
    }
}