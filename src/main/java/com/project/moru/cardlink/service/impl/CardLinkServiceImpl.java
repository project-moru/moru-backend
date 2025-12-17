package com.project.moru.cardlink.service.impl;

import com.project.moru.cardlink.domain.dto.CardLinkBlockCreateRequestDto;
import com.project.moru.cardlink.domain.dto.CardLinkBlockResponseDto;
import com.project.moru.cardlink.domain.entity.CardLinkBlock;
import com.project.moru.cardlink.mapper.CardLinkConverter;
import com.project.moru.cardlink.service.CardLinkService;
import com.project.moru.cardlink.service_data.CardLinkDataService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
@Transactional
public class CardLinkServiceImpl implements CardLinkService {
  
  private final CardLinkConverter cardLinkConverter;
  private final CardLinkDataService cardLinkDataService;
  
  @Override
  @Transactional(readOnly = true)
  public List<CardLinkBlockResponseDto> getListByLinkBlock(Long linkBlockId) {
    return cardLinkConverter.toDtoList(cardLinkDataService.findAllByLinkBlockId(linkBlockId));
  }
  
  @Override
  public CardLinkBlockResponseDto register(CardLinkBlockCreateRequestDto dto) {
    CardLinkBlock cardLinkBlock = cardLinkConverter.toEntity(dto);
    return cardLinkConverter.toDto(cardLinkDataService.save(cardLinkBlock));
  }
  
  @Override
  public void delete(Long cardLinkBlockId) {
    cardLinkDataService.deleteById(cardLinkBlockId);
  }
}