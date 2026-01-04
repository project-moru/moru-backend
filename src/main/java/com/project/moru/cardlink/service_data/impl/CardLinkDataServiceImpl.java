package com.project.moru.cardlink.service_data.impl;

import com.project.moru.cardlink.domain.entity.CardLinkBlock;
import com.project.moru.cardlink.repository.CardLinkBlockRepository;
import com.project.moru.cardlink.service_data.CardLinkDataService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CardLinkDataServiceImpl implements CardLinkDataService {
  
  private final CardLinkBlockRepository cardLinkBlockRepository;
  
  @Override
  public List<CardLinkBlock> findAllByLinkBlockId(Long linkBlockId) {
    return cardLinkBlockRepository.findAllByLinkBlockId(linkBlockId);
  }
  
  @Override
  public CardLinkBlock save(CardLinkBlock cardLinkBlock) {
    return cardLinkBlockRepository.save(cardLinkBlock);
  }
  
  @Override
  public void deleteById(Long cardLinkBlockId) {
    cardLinkBlockRepository.deleteById(cardLinkBlockId);
  }
}