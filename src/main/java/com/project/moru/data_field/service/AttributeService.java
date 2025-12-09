package com.project.moru.data_field.service;

import com.project.moru.data_field.domain.dto.AttributeCreateRequestDto;
import com.project.moru.data_field.domain.dto.AttributeResponseDto;
import com.project.moru.data_field.domain.dto.AttributeUpdateRequestDto;

import java.util.List;

public interface AttributeService {
  List<AttributeResponseDto> getListByDataField(Long dataFieldId);
  AttributeResponseDto register(AttributeCreateRequestDto dto, Long userId);
  AttributeResponseDto update(Long attributeId, AttributeUpdateRequestDto dto);
  void delete(Long attributeId);
}
