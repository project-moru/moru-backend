package com.project.moru.card.domain.dto;

import com.project.moru.common.constant.Status;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
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

    private Status isPublic = Status.PUBLIC;

    @Schema(description = "이미지 주소", example = "http://xxx.xxxx.xxx")
    private String imageUrl;
}