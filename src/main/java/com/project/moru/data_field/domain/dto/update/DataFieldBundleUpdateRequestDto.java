package com.project.moru.data_field.domain.dto.update;

import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DataFieldBundleUpdateRequestDto {
  private DataFieldUpdateRequestDto dataField;
  private List<AttributeUpdateRequestDto> attributeBlocks;
  private List<LinkUpdateRequestDto> linkBlocks;
}