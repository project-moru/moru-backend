package com.project.moru.data_field.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class LinkResponseDto {
  private Long id;
  private Long dataFieldId;
  private String name;
  private Integer maxLinkCount;
}
