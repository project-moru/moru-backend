package com.project.moru.data_field.service_data;

import com.project.moru.data_field.domain.entity.DataField;

import java.util.List;

public interface DataFieldDataService {
  DataField findById(Long dataFieldId);
  
  DataField save(DataField dataField);
  
  void deleteById(Long dataFieldId);
  
  List<DataField> findDataFieldsByUserId(Long userId);
}
