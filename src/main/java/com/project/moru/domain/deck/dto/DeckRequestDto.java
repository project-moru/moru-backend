package com.project.moru.domain.deck.dto;

import com.project.moru.domain.deck.entity.Status;
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
    private String title;
    private String content;
    private Status status;
    @Builder.Default
    private List<Long> cardIds = new ArrayList<>();
}
