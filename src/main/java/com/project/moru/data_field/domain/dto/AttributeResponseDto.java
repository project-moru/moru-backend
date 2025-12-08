package com.project.moru.data_field.domain.dto;

import com.project.moru.data_field.constant.Type;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class AttributeResponseDto {
  private Long id;
  private Long dataFieldId;
  private String name;
  private String placeHolder;
  private Type type;
}
