package com.project.moru.data_field.service.impl;

import com.project.moru.common.exception.ErrorCode;
import com.project.moru.common.exception.GeneralException;
import com.project.moru.data_field.domain.dto.AttributeCreateRequestDto;
import com.project.moru.data_field.domain.dto.AttributeResponseDto;
import com.project.moru.data_field.domain.dto.AttributeUpdateRequestDto;
import com.project.moru.data_field.domain.entity.AttributeBlock;
import com.project.moru.data_field.domain.entity.DataField;
import com.project.moru.data_field.mapper.AttributeBlockConverter;
import com.project.moru.data_field.service.AttributeBlockService;
import com.project.moru.data_field.service_data.AttributeBlockDataService;
import com.project.moru.data_field.service_data.DataFieldDataService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
@Transactional
public class AttributeBlockServiceImpl implements AttributeBlockService {
  
  private final AttributeBlockDataService attributeBlockDataService;
  private final AttributeBlockConverter attributeBlockConverter;
  
  private final DataFieldDataService dataFieldDataService;
  
  @Override
  @Transactional(readOnly = true)
  public List<AttributeResponseDto> getListByDataField(Long dataFieldId) {
    return attributeBlockConverter.toDtoList(
        attributeBlockDataService.findAttributesByDataFieldId(dataFieldId)
    );
  }
  
  @Override
  public AttributeResponseDto register(AttributeCreateRequestDto dto, Long userId) {
    DataField dataField = dataFieldDataService.findById(dto.getDataFieldId());
    
    if (!dataField.getUser().getId().equals(userId)) {
      throw new GeneralException(ErrorCode.ACCESS_DENIED);
    }
    
    AttributeBlock attributeBlock = attributeBlockConverter.toEntity(dto);
    dataField.addAttribute(attributeBlock);
    attributeBlock = attributeBlockDataService.save(attributeBlock);
    return attributeBlockConverter.toDto(attributeBlock);
  }
  
  @Override
  public AttributeResponseDto update(Long attributeId, AttributeUpdateRequestDto dto) {
    AttributeBlock attributeBlock = attributeBlockDataService.findById(attributeId);
    attributeBlock.update(dto);
    return attributeBlockConverter.toDto(attributeBlock);
  }
  
  @Override
  public void delete(Long attributeId) {
    attributeBlockDataService.deleteById(attributeId);
  }
}
