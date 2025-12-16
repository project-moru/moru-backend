package com.project.moru.data_field.mapper;

import com.project.moru.data_field.domain.dto.DataFieldCreateRequestDto;
import com.project.moru.data_field.domain.dto.DataFieldResponseDto;
import com.project.moru.data_field.domain.entity.AttributeBlock;
import com.project.moru.data_field.domain.entity.DataField;
import com.project.moru.data_field.domain.entity.LinkBlock;
import com.project.moru.user.domain.entity.User;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;

@Mapper(componentModel = "spring", builder = @Builder())
public interface DataFieldConverter {
  
  @Mapping(target = "userId", source = "user.id")
  @Mapping(target = "attributeCnt", source = "attributeBlocks", qualifiedByName = "countAttributes")
  @Mapping(target = "linkCnt", source = "linkBlocks", qualifiedByName = "countLinks")
  DataFieldResponseDto toDto(DataField dataField);
  
  @Mapping(target = "user", source = "user")
  @Mapping(target = "attributeBlocks", ignore = true)
  DataField toEntity(DataFieldCreateRequestDto dto, User user);
  
  List<DataFieldResponseDto> toDtoList(List<DataField> dataFields);
  
  @Named("countAttributes")
  default int countAttributes(List<AttributeBlock> attributeBlocks) {
    return attributeBlocks == null ? 0 : attributeBlocks.size();
  }
  
  @Named("countLinks")
  default int countLinks(List<LinkBlock> linkBlocks) {
    return linkBlocks == null ? 0 : linkBlocks.size();
  }
}