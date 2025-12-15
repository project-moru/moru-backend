package com.project.moru.data_field.service;

import com.project.moru.data_field.domain.dto.AttributeCreateRequestDto;
import com.project.moru.data_field.domain.dto.AttributeResponseDto;
import com.project.moru.data_field.domain.dto.AttributeUpdateRequestDto;

import java.util.List;

public interface AttributeBlockService {
  List<AttributeResponseDto> getListByDataField(Long dataFieldId);
  AttributeResponseDto register(AttributeCreateRequestDto dto, Long userId);
  AttributeResponseDto update(Long attributeBlockId, AttributeUpdateRequestDto dto);
  void delete(Long attributeBlockId);
}
