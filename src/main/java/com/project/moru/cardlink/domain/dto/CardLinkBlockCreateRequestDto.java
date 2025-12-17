package com.project.moru.cardlink.domain.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CardLinkBlockCreateRequestDto {
  @NotBlank
  @Schema(description = "카드 ID", example = "1")
  private Long cardId;
  
  @NotBlank
  @Schema(description = "연결 블록 ID", example = "1")
  private Long linkBlockId;
}