package com.project.moru.domain.deck;

import com.project.moru.domain.deck.dto.DeckRequestDto;
import com.project.moru.domain.deck.dto.DeckResponseDto;
import com.project.moru.domain.deck.entity.Deck;
import com.project.moru.domain.entity.user.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface DeckMapper {

    @Mapping(source = "user.id", target = "userId")
    DeckResponseDto toDto(Deck deck);

    @Mapping(target = "user", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "deckCards", ignore = true)
    Deck toEntity(DeckRequestDto deckRequestDto, User user);
}
