package com.project.moru.card.domain.dto;

import com.project.moru.common.constant.Status;
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
    @Size(max = 100, message = "카드 제목은 100자를 초과할 수 없습니다.")
    private String cardName;

    @Size(max = 5000, message = "카드 내용은 5000자를 초과할 수 없습니다.")
    private String cardContent;

    @Builder.Default
    private Status isPublic = Status.PUBLIC;

    private String imageUrl;
}