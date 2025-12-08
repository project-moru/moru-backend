package com.project.moru.data_field.mapper;

import com.project.moru.data_field.domain.dto.AttributesCreateRequestDto;
import com.project.moru.data_field.domain.dto.AttributesResponseDto;
import com.project.moru.data_field.domain.entity.Attributes;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", builder = @Builder())
public interface AttributesConverter {
  @Mapping(target = "dataField.id", source = "dataFieldId")
  Attributes toEntity(AttributesCreateRequestDto dto);
  @Mapping(target = "dataFieldId", source = "dataField.id")
  AttributesResponseDto toDto(Attributes attributes);
  List<AttributesResponseDto> toDtoList(List<Attributes> attributes);
}
