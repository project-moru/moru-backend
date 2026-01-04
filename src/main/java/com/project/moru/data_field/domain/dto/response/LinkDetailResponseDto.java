package com.project.moru.data_field.domain.dto.response;

import com.project.moru.cardlink.domain.dto.CardLinkBlockResponseDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@Getter
@Setter
public class LinkDetailResponseDto {
  private Long id;
  private Long dataFieldId;
  private String name;
  private Integer maxLinkCount;
  
  private List<CardLinkBlockResponseDto> cardLinkBlocks;
}
