package com.project.moru.data_field.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class DataFieldResponseDto {
  private Long id;
  private Long userId;
  private String name;
  private String description;
}
