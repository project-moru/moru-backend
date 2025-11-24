package com.project.moru.mapper.struct;

import com.project.moru.domain.dto.card.CardCreateRequestDto;
import com.project.moru.domain.dto.card.CardResponseDto;
import com.project.moru.domain.entity.card.Card;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CardConverter {
    Card fromCreateReqToEntity(CardCreateRequestDto dto);

    CardResponseDto fromEntityToRes(Card card);

    List<CardResponseDto> toResList(List<Card> cards);
}
