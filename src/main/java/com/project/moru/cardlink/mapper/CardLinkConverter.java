package com.project.moru.cardlink.mapper;

import com.project.moru.cardlink.domain.dto.CardLinkBlockCreateRequestDto;
import com.project.moru.cardlink.domain.dto.CardLinkBlockResponseDto;
import com.project.moru.cardlink.domain.entity.CardLinkBlock;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", builder = @Builder())
public interface CardLinkConverter {
  
  @Mapping(target = "cardId", source = "card.id")
  @Mapping(target = "linkBlockId", source = "linkBlock.id")
  CardLinkBlockResponseDto toDto(CardLinkBlock cardLinkBlock);
  
  @Mapping(target = "card.id", source = "cardId")
  @Mapping(target = "linkBlock.id", source = "linkBlockId")
  CardLinkBlock toEntity(CardLinkBlockCreateRequestDto dto);
  
  List<CardLinkBlockResponseDto> toDtoList(List<CardLinkBlock> cardLinkBlocks);
}