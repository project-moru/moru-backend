package com.project.moru.deck.mapper;

import com.project.moru.deck.domain.dto.DeckRequestDto;
import com.project.moru.deck.domain.dto.DeckResponseDto;
import com.project.moru.deck.domain.entity.Deck;
import com.project.moru.user.domain.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface DeckConverter {
//
//    @Mapping(source = "user.userId", target = "userId")
    DeckResponseDto toDto(Deck deck);
//
//    @Mapping(target = "user", ignore = true)
//    @Mapping(target = "id", ignore = true)
//    @Mapping(target = "deckCards", ignore = true)
    Deck toEntity(DeckRequestDto deckRequestDto, User user);
}
