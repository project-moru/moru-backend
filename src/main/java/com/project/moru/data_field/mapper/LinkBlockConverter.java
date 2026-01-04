package com.project.moru.data_field.mapper;

import com.project.moru.cardlink.mapper.CardLinkConverter;
import com.project.moru.data_field.domain.dto.create.LinkCreateRequestDto;
import com.project.moru.data_field.domain.dto.response.LinkDetailResponseDto;
import com.project.moru.data_field.domain.dto.response.LinkResponseDto;
import com.project.moru.data_field.domain.entity.LinkBlock;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(
    componentModel = "spring",
    builder = @Builder(),
    uses = CardLinkConverter.class
)
public interface LinkBlockConverter {
  
  @Mapping(target = "dataFieldId", source = "dataField.id")
  LinkResponseDto toDto(LinkBlock linkBlock);
  
  @Mapping(target = "dataFieldId", source = "dataField.id")
  LinkDetailResponseDto toDetailDto(LinkBlock linkBlock);
  
  LinkBlock toEntity(LinkCreateRequestDto dto);
  
  List<LinkResponseDto> toDtoList(List<LinkBlock> linkBlocks);
  List<LinkDetailResponseDto> toDetailDtoList(List<LinkBlock> linkBlocks);
}
