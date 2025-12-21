package com.project.moru.cardlink.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CardLinkBlockResponseDto {
  private Long id;
  private Long cardId;
  private String cardName;
  private String imageUrl;
  private Long linkBlockId;
}