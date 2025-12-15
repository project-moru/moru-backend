package com.project.moru.data_field.service_data;

import com.project.moru.data_field.domain.entity.AttributeBlock;

import java.util.List;

public interface AttributeBlockDataService {
  AttributeBlock findById(Long attributeId);
  
  AttributeBlock save(AttributeBlock attributeBlock);
  
  void deleteById(Long attributeId);
  
  List<AttributeBlock> findAttributesByDataFieldId(Long dataFieldId);
}
