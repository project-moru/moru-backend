package com.project.moru.data_field.domain.dto.create;

import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DataFieldBundleCreateRequestDto {
  private DataFieldCreateRequestDto dataField;
  private List<AttributeCreateRequestDto> attributeBlocks;
  private List<LinkCreateRequestDto> linkBlocks;
}