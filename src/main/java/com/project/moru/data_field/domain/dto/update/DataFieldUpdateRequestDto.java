package com.project.moru.data_field.domain.dto.update;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class DataFieldUpdateRequestDto {
  @Schema(description = "필드 이름", example = "문화재")
  @NotBlank(message = "필드 이름은 필수입니다.")
  private String name;
  
  @Schema(description = "필드 설명", example = "국가 지정 문화재 정보를 관리합니다.")
  private String description;
}
