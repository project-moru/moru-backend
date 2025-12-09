package com.project.moru.data_field.mapper;

import com.project.moru.data_field.domain.dto.DataFieldCreateRequestDto;
import com.project.moru.data_field.domain.dto.DataFieldResponseDto;
import com.project.moru.data_field.domain.entity.DataField;
import com.project.moru.user.domain.entity.User;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", builder = @Builder())
public interface DataFieldConverter {
  
  @Mapping(target = "userId", source = "user.id")
  DataFieldResponseDto toDto(DataField dataField);
  
  @Mapping(target = "user", source = "user")
  @Mapping(target = "attributes", ignore = true)
  DataField toEntity(DataFieldCreateRequestDto dto, User user);
  
  List<DataFieldResponseDto> toDtoList(List<DataField> dataFields);
}