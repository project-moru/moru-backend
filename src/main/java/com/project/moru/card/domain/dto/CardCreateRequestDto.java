package com.project.moru.card.domain.dto;

import com.project.moru.common.constant.Status;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CardCreateRequestDto {

    @NotBlank(message = "카드 제목은 필수입니다.")
    @Schema(description = "카드 제목", example = "card_title")
    @Size(max = 100, message = "카드 제목은 100자를 초과할 수 없습니다.")
    private String cardName;

    @Size(max = 5000, message = "카드 내용은 5000자를 초과할 수 없습니다.")
    @Schema(description = "카드 내용", example = "card_content")
    private String cardContent;

    @Schema(description = "덱 공개 범위", example = "PUBLIC")
    private Status status = Status.PUBLIC;
}