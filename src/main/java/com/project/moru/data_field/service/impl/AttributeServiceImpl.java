package com.project.moru.data_field.service.impl;

import com.project.moru.data_field.domain.dto.AttributeCreateRequestDto;
import com.project.moru.data_field.domain.dto.AttributeResponseDto;
import com.project.moru.data_field.domain.dto.AttributeUpdateRequestDto;
import com.project.moru.data_field.domain.entity.Attribute;
import com.project.moru.data_field.mapper.AttributeConverter;
import com.project.moru.data_field.service.AttributeService;
import com.project.moru.data_field.service_data.AttributeDataService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
@Transactional
public class AttributeServiceImpl implements AttributeService {
  
  private final AttributeDataService attributeDataService;
  private final AttributeConverter attributeConverter;
  
  @Override
  @Transactional(readOnly = true)
  public List<AttributeResponseDto> getListByDataField(Long dataFieldId) {
    return attributeConverter.toDtoList(
        attributeDataService.findAttributesByDataFieldId(dataFieldId)
    );
  }
  
  @Override
  public AttributeResponseDto register(AttributeCreateRequestDto dto) {
    Attribute attribute = attributeConverter.toEntity(dto);
    return attributeConverter.toDto(attributeDataService.save(attribute));
  }
  
  @Override
  public AttributeResponseDto update(Long attributeId, AttributeUpdateRequestDto dto) {
    Attribute attribute = attributeDataService.findById(attributeId);
    attribute.update(dto);
    return attributeConverter.toDto(attribute);
  }
  
  @Override
  public void delete(Long attributeId) {
    attributeDataService.deleteById(attributeId);
  }
}
