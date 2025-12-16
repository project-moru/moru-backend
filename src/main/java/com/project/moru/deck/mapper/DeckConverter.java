package com.project.moru.deck.mapper;

import com.project.moru.deck.domain.dto.DeckRequestDto;
import com.project.moru.deck.domain.dto.DeckResponseDto;
import com.project.moru.deck.domain.entity.Deck;
import com.project.moru.deck.domain.entity.DeckCard;
import com.project.moru.user.domain.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface DeckConverter {

    @Mapping(source = "user.id", target = "userId")
    @Mapping(source = "status", target = "status")
    @Mapping(source = "deckCards", target = "cardIds")
    DeckResponseDto toDto(Deck deck);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "deckCards", ignore = true)
    @Mapping(source = "user", target = "user")
    Deck toEntity(DeckRequestDto deckRequestDto, User user);

    default List<Long> mapDeckCardsToIds(List<DeckCard> deckCards) {
        if (deckCards == null) {
            return null;
        }
        return deckCards.stream()
                .map(deckCard -> deckCard.getCard().getId())
                .collect(Collectors.toList());
    }
}
