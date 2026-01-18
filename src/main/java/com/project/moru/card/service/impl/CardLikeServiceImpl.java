package com.project.moru.card.service.impl;

import com.project.moru.card.domain.dto.CardResponseDto;
import com.project.moru.card.domain.entity.Card;
import com.project.moru.card.mapper.CardConverter;
import com.project.moru.card.repository.CardRepository;
import com.project.moru.card.service.CardLikeService;
import com.project.moru.common.exception.ErrorCode;
import com.project.moru.common.exception.GeneralException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class CardLikeServiceImpl implements CardLikeService {

    private final RedisTemplate<String, String> redisTemplate;
    private final CardRepository cardRepository;
    private final CardConverter cardConverter;

    @Transactional
    public CardResponseDto toggleLike(Long cardId, Long userId) {
        String key = "card:like:" + cardId;
        String member = String.valueOf(userId);

        Boolean isLiked = redisTemplate.opsForSet().isMember(key, member);
        Card card = cardRepository.findById(cardId)
                .orElseThrow(() -> new GeneralException(ErrorCode.NOT_FOUND_CARD));

        if (Boolean.TRUE.equals(isLiked)) {
            redisTemplate.opsForSet().remove(key, member);
        } else {
            redisTemplate.opsForSet().add(key, member);
        }

        Long count = redisTemplate.opsForSet().size(key);

        return cardConverter.fromEntityToRes(card).updateLike(count);
    }

    public Long getLikeCount(Long cardId) {
        String key = "card:like:" + cardId;
        return redisTemplate.opsForSet().size(key);
    }
}
