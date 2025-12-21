package com.project.moru.cardlink.service_data;

import com.project.moru.cardlink.domain.entity.CardLinkBlock;

import java.util.List;

public interface CardLinkDataService {
  List<CardLinkBlock> findAllByLinkBlockId(Long linkBlockId); // 연결블록에 연결된 카드 리스트 조회
  CardLinkBlock save(CardLinkBlock cardLinkBlock);
  void deleteById(Long cardLinkBlockId);
}