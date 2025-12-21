package com.project.moru.card.service;

import com.project.moru.card.domain.dto.CardCreateRequestDto;
import com.project.moru.card.domain.dto.CardResponseDto;
import com.project.moru.card.domain.dto.CardUpdateRequestDto;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface CardService {
    CardResponseDto findById(Long id, Long userId);
    void deleteCardById(Long id, Long userId);
    CardResponseDto saveCard(CardCreateRequestDto cardCreateRequestDto, Long userId, MultipartFile multipartFile);
    CardResponseDto modifyCard(Long id, CardUpdateRequestDto cardUpdateRequestDto, Long userId);
    List<CardResponseDto> findAll(Long userId);

}
