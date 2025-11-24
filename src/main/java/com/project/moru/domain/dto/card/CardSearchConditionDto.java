package com.project.moru.domain.dto.card;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@Getter
public class CardSearchConditionDto {
  private String cardName;
  private List<String> tags; // 검색 태그 목록 (["혼구문발", "백사토", "조선시대"])
}