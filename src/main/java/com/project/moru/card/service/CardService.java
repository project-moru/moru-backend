package com.project.moru.card.service;

import com.project.moru.card.domain.dto.CardCreateRequestDto;
import com.project.moru.card.domain.dto.CardUpdateRequestDto;
import com.project.moru.card.domain.entity.Card;

import java.util.List;

public interface CardService {
    List<Card> findAll();
    Card findById(Long id);
    Card saveCard(CardCreateRequestDto cardCreateRequestDto, Long userId);
    Card modifyCard(Long id, CardUpdateRequestDto cardUpdateRequestDto, Long userId);
    void deleteCardById(Long id, Long userId);

}
