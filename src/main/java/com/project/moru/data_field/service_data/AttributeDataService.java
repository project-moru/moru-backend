package com.project.moru.data_field.service_data;

import com.project.moru.data_field.domain.entity.Attribute;

import java.util.List;

public interface AttributeDataService {
  Attribute findById(Long attributeId);
  
  Attribute save(Attribute attribute);
  
  void deleteById(Long attributeId);
  
  List<Attribute> findAttributesByDataFieldId(Long dataFieldId);
}
