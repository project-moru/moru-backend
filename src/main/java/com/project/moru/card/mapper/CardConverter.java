package com.project.moru.card.mapper;

import com.project.moru.card.domain.dto.CardCreateRequestDto;
import com.project.moru.card.domain.dto.CardResponseDto;
import com.project.moru.card.domain.entity.Card;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CardConverter {
    Card fromCreateReqToEntity(CardCreateRequestDto dto);

    CardResponseDto fromEntityToRes(Card card);

    List<CardResponseDto> toResList(List<Card> cards);
}
