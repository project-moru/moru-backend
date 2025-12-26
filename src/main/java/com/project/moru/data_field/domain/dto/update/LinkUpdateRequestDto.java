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
public class LinkUpdateRequestDto {
  @Schema(description = "연결 블록 이름", example = "테스트 블록")
  @NotBlank(message = "블록 이름은 필수입니다.")
  private String name;
  
  @Schema(description = "최대 연결 수", example = "2")
  private Integer maxLinkCount;
}