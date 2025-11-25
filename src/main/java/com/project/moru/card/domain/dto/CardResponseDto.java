package com.project.moru.card.domain.dto;

import com.project.moru.common.constant.Status;
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