package com.project.moru.user.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class DataFieldChangeRequestDto implements ChangeDataField {
  private Long defaultDataFieldId;
}