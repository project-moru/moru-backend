package com.project.moru.data_field.mapper;

import com.project.moru.data_field.domain.dto.AttributesCreateRequestDto;
import com.project.moru.data_field.domain.dto.AttributesResponseDto;
import com.project.moru.data_field.domain.entity.Attribute;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", builder = @Builder())
public interface AttributeConverter {
  @Mapping(target = "dataField.id", source = "dataFieldId")
  Attribute toEntity(AttributesCreateRequestDto dto);
  @Mapping(target = "dataFieldId", source = "dataField.id")
  AttributesResponseDto toDto(Attribute attribute);
  List<AttributesResponseDto> toDtoList(List<Attribute> attributes);
}
