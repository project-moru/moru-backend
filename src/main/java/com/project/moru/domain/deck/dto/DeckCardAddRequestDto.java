package com.project.moru.domain.deck.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import java.util.List;

@Getter
@NoArgsConstructor
public class DeckCardAddRequestDto {
    @NotEmpty(message = "추가할 카드를 선택해주세요")
    private List<Long> cardIds;
}
