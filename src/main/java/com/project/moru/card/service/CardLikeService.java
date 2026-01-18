package com.project.moru.card.service;

import com.project.moru.card.domain.dto.CardResponseDto;

public interface CardLikeService {
    CardResponseDto toggleLike(Long cardId, Long userId);
    Long getLikeCount(Long cardId);
}
