package com.project.moru.data_field.mapper;

import com.project.moru.data_field.domain.dto.LinkCreateRequestDto;
import com.project.moru.data_field.domain.dto.LinkResponseDto;
import com.project.moru.data_field.domain.entity.LinkBlock;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", builder = @Builder())
public interface LinkBlockConverter {
  
  @Mapping(target = "dataFieldId", source = "dataField.id")
  LinkResponseDto toDto(LinkBlock linkBlock);
  
  @Mapping(target = "dataField.id", source = "dataFieldId")
  LinkBlock toEntity(LinkCreateRequestDto dto);
  
  List<LinkResponseDto> toDtoList(List<LinkBlock> linkBlocks);
}
