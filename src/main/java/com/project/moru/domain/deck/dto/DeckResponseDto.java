package com.project.moru.domain.deck.dto;

import com.project.moru.common.constant.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DeckResponseDto {
    private Long id;
    private Long userId;
    private String title;
    private String content;
    private Status status;
    private List<Long> cardIds;
}
