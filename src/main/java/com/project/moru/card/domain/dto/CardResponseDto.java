package com.project.moru.card.domain.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
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
    private final Status status;
    private final Integer tagCount;
    private final Integer viewCount;
    private final Integer likeCount;
    private final Long userId;
    private final String userNickname;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private final LocalDateTime createdAt;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private final LocalDateTime updatedAt;
}