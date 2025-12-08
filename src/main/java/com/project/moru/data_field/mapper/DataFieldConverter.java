package com.project.moru.data_field.mapper;

import com.project.moru.data_field.domain.dto.DataFieldCreateRequestDto;
import com.project.moru.data_field.domain.dto.DataFieldResponseDto;
import com.project.moru.data_field.domain.entity.DataField;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", builder = @Builder())
public interface DataFieldConverter {
  @Mapping(target = "user.id", source = "userId")
  DataField toEntity(DataFieldCreateRequestDto dto);
  @Mapping(target = "userId", source = "user.id")
  DataFieldResponseDto toDto(DataField dataField);
  List<DataFieldResponseDto> toDtoList(List<DataField> dataFields);
}
