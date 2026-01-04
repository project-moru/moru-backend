package com.project.moru.cardlink.service;

import com.project.moru.cardlink.domain.dto.CardLinkBlockCreateRequestDto;
import com.project.moru.cardlink.domain.dto.CardLinkBlockResponseDto;

import java.util.List;

public interface CardLinkService {
  List<CardLinkBlockResponseDto> getListByLinkBlock(Long linkBlockId);
  CardLinkBlockResponseDto register(CardLinkBlockCreateRequestDto dto);
  void delete(Long cardLinkBlockId);
}