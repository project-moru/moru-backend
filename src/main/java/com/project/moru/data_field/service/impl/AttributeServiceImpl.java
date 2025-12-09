package com.project.moru.data_field.service.impl;

import com.project.moru.common.exception.ErrorCode;
import com.project.moru.common.exception.GeneralException;
import com.project.moru.data_field.domain.dto.AttributeCreateRequestDto;
import com.project.moru.data_field.domain.dto.AttributeResponseDto;
import com.project.moru.data_field.domain.dto.AttributeUpdateRequestDto;
import com.project.moru.data_field.domain.entity.Attribute;
import com.project.moru.data_field.domain.entity.DataField;
import com.project.moru.data_field.mapper.AttributeConverter;
import com.project.moru.data_field.service.AttributeService;
import com.project.moru.data_field.service.DataFieldService;
import com.project.moru.data_field.service_data.AttributeDataService;
import com.project.moru.data_field.service_data.DataFieldDataService;
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
  
  private final DataFieldDataService dataFieldDataService;
  
  @Override
  @Transactional(readOnly = true)
  public List<AttributeResponseDto> getListByDataField(Long dataFieldId) {
    return attributeConverter.toDtoList(
        attributeDataService.findAttributesByDataFieldId(dataFieldId)
    );
  }
  
  @Override
  public AttributeResponseDto register(AttributeCreateRequestDto dto, Long userId) {
    DataField dataField = dataFieldDataService.findById(dto.getDataFieldId());
    
    if (!dataField.getUser().getId().equals(userId)) {
      throw new GeneralException(ErrorCode.ACCESS_DENIED);
    }
    
    Attribute attribute = attributeConverter.toEntity(dto);
    dataField.addAttribute(attribute);
    attribute = attributeDataService.save(attribute);
    return attributeConverter.toDto(attribute);
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
