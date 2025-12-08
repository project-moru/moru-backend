package com.project.moru.data_field.domain.dto;

import com.project.moru.data_field.constant.Type;
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
public class AttributesUpdateRequestDto {
  @Schema(description = "속성 블록 이름", example = "문화재")
  @NotBlank(message = "블록 이름은 필수입니다.")
  private String name;
  
  @Schema(description = "플레이스 홀더", example = "문화재의 정식 명칭을 입력하세요.")
  private String placeHolder;
  
  @Schema(
      description = "속성 블록 타입", example = "TEXT",
      allowableValues = {"TEXT", "IMAGE", "NUMBER"}
  )
  private Type type;
}
