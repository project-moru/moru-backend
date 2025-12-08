package com.project.moru.data_field.service_data.impl;

import com.project.moru.data_field.domain.entity.Attribute;
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
  public Attribute findById(Long attributeId) {
    return attributeRepository.findById(attributeId)
        .orElseThrow();
  }
  
  @Override
  public Attribute save(Attribute attribute) {
    return attributeRepository.save(attribute);
  }
  
  @Override
  public void deleteById(Long attributeId) {
    attributeRepository.deleteById(attributeId);
  }
  
  @Override
  public List<Attribute> findAttributesByDataFieldId(Long dataFieldId) {
    return attributeRepository.findAttributesByDataFieldId(dataFieldId);
  }
}