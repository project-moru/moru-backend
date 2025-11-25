package com.project.moru.service;

import com.project.moru.domain.dto.card.CardCreateRequestDto;
import com.project.moru.domain.dto.card.CardResponseDto;
import com.project.moru.domain.dto.card.CardUpdateRequestDto;
import com.project.moru.domain.entity.card.Card;

import java.util.List;

public interface CardService {
    CardResponseDto findById(Long id);
    void deleteCardById(Long id, Long userId);
    CardResponseDto saveCard(CardCreateRequestDto cardCreateRequestDto, Long userId);
    CardResponseDto modifyCard(Long id, CardUpdateRequestDto cardUpdateRequestDto, Long userId);
    List<CardResponseDto> findAll();

}
