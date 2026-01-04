package com.project.moru.data_field.service;

import com.project.moru.data_field.domain.dto.create.AttributeCreateRequestDto;
import com.project.moru.data_field.domain.dto.response.AttributeResponseDto;
import com.project.moru.data_field.domain.dto.update.AttributeUpdateRequestDto;

import java.util.List;

public interface AttributeBlockService {
  List<AttributeResponseDto> getListByDataField(Long dataFieldId);
  AttributeResponseDto register(Long dataFieldId, AttributeCreateRequestDto dto, Long userId);
  AttributeResponseDto update(Long attributeBlockId, AttributeUpdateRequestDto dto);
  void delete(Long attributeBlockId);
}
