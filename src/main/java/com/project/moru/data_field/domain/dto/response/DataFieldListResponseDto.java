package com.project.moru.data_field.domain.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@Getter
@Setter
@Builder
public class DataFieldListResponseDto {
  private Long defaultDataFieldId;
  private List<DataFieldResponseDto> dataFields;
}
