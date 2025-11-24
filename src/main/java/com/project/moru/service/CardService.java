package com.project.moru.service;

import com.project.moru.domain.dto.card.CardCreateRequestDto;
import com.project.moru.domain.dto.card.CardUpdateRequestDto;
import com.project.moru.domain.entity.card.Card;

import java.util.List;

public interface CardService {
    List<Card> findAll();
    Card findById(Long id);
    Card saveCard(CardCreateRequestDto cardCreateRequestDto, Long userId);
    Card modifyCard(Long id, CardUpdateRequestDto cardUpdateRequestDto, Long userId);
    void deleteCardById(Long id, Long userId);

}
