package com.project.moru.data_field.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Builder
@AllArgsConstructor
@Getter
@Setter
public class DataFieldDetailResponseDto {
  private final DataFieldResponseDto dataField;
  private final List<AttributeResponseDto> attributeBlocks;
  private final List<LinkDetailResponseDto> linkBlocks;
}
