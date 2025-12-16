package com.project.moru.deck.domain.dto;

import com.project.moru.common.constant.Status;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DeckRequestDto {
    @Schema(description = "덱 이름", example = "테스트 덱")
    private String title;

    @Schema(description = "덱 설명", example = "덱에 대한 설명")
    private String content;

    @Schema(description = "덱 공개 범위", example = "PUBLIC")
    private Status status;
}
