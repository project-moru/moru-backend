package com.project.moru.data_field.mapper;

import com.project.moru.data_field.domain.dto.create.AttributeCreateRequestDto;
import com.project.moru.data_field.domain.dto.response.AttributeResponseDto;
import com.project.moru.data_field.domain.entity.AttributeBlock;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", builder = @Builder())
public interface AttributeBlockConverter {
  
  @Mapping(target = "dataFieldId", source = "dataField.id")
  AttributeResponseDto toDto(AttributeBlock attributeBlock);
  
  AttributeBlock toEntity(AttributeCreateRequestDto dto);
  
  List<AttributeResponseDto> toDtoList(List<AttributeBlock> attributeBlocks);
}
