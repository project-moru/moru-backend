package com.project.moru.data_field.service_data.impl;

import com.project.moru.data_field.domain.entity.AttributeBlock;
import com.project.moru.data_field.repository.AttributeRepository;
import com.project.moru.data_field.service_data.AttributeDataService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class AttributeDataServiceImpl implements AttributeDataService {
  
  private final AttributeRepository attributeRepository;
  
  @Override
  public AttributeBlock findById(Long attributeId) {
    return attributeRepository.findById(attributeId)
        .orElseThrow();
  }
  
  @Override
  public AttributeBlock save(AttributeBlock attributeBlock) {
    return attributeRepository.save(attributeBlock);
  }
  
  @Override
  public void deleteById(Long attributeId) {
    attributeRepository.deleteById(attributeId);
  }
  
  @Override
  public List<AttributeBlock> findAttributesByDataFieldId(Long dataFieldId) {
    return attributeRepository.findAttributesByDataFieldId(dataFieldId);
  }
}