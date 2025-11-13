package com.project.moru.service;

import com.project.moru.domain.dto.card.CardCreateRequestDto;
import com.project.moru.domain.dto.card.CardUpdateRequestDto;
import com.project.moru.domain.entity.card.Card;

import java.util.List;

public interface CardService {
    Card findById(Long id);
    void deleteCardById(Long id, Long userId);
    Card saveCard(CardCreateRequestDto cardCreateRequestDto, Long userId);
    Card modifyCard(CardUpdateRequestDto cardUpdateRequestDto, Long userId);
    List<Card> findAll();

}
