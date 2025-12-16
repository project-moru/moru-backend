package com.project.moru.data_field.service_data.impl;

import com.project.moru.data_field.domain.entity.AttributeBlock;
import com.project.moru.data_field.repository.AttributeBlockRepository;
import com.project.moru.data_field.service_data.AttributeBlockDataService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class AttributeBlockDataServiceImpl implements AttributeBlockDataService {
  
  private final AttributeBlockRepository attributeBlockRepository;
  
  @Override
  public AttributeBlock findById(Long attributeId) {
    return attributeBlockRepository.findById(attributeId)
        .orElseThrow();
  }
  
  @Override
  public AttributeBlock save(AttributeBlock attributeBlock) {
    return attributeBlockRepository.save(attributeBlock);
  }
  
  @Override
  public void deleteById(Long attributeId) {
    attributeBlockRepository.deleteById(attributeId);
  }
  
  @Override
  public List<AttributeBlock> findAttributesByDataFieldId(Long dataFieldId) {
    return attributeBlockRepository.findAttributesByDataFieldId(dataFieldId);
  }
}