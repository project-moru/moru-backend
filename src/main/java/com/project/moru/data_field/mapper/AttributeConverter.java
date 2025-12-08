package com.project.moru.data_field.mapper;

import com.project.moru.data_field.domain.dto.AttributeCreateRequestDto;
import com.project.moru.data_field.domain.dto.AttributeResponseDto;
import com.project.moru.data_field.domain.entity.Attribute;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", builder = @Builder())
public interface AttributeConverter {
  @Mapping(target = "dataField.id", source = "dataFieldId")
  Attribute toEntity(AttributeCreateRequestDto dto);
  @Mapping(target = "dataFieldId", source = "dataField.id")
  AttributeResponseDto toDto(Attribute attribute);
  List<AttributeResponseDto> toDtoList(List<Attribute> attributes);
}
