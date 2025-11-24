package com.project.moru.domain.dto.card;

import com.project.moru.domain.deck.entity.Status;
import com.project.moru.domain.entity.card.Card;
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
    private final Status isPublic;
    private final Integer tagCount;
    private final Integer viewCount;
    private final Integer likeCount;
    private final Long userId;
    private final String userNickname;
    private final LocalDateTime createdAt;
    private final LocalDateTime updatedAt;
}