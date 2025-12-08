package com.project.moru.data_field.service_data.impl;

import com.project.moru.data_field.domain.entity.DataField;
import com.project.moru.data_field.repository.DataFieldRepository;
import com.project.moru.data_field.service_data.DataFieldDataService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class DataFieldDataServiceImpl implements DataFieldDataService {
  
  private final DataFieldRepository dataFieldRepository;
  
  @Override
  public DataField findById(Long dataFieldId) {
    return dataFieldRepository.findById(dataFieldId)
        .orElseThrow();
  }
  
  @Override
  public DataField save(DataField dataField) {
    return dataFieldRepository.save(dataField);
  }
  
  @Override
  public void deleteById(Long dataFieldId) {
    dataFieldRepository.deleteById(dataFieldId);
  }
  
  @Override
  public List<DataField> findDataFieldsByUserId(Long userId) {
    return dataFieldRepository.findDataFieldsByUserId(userId);
  }
}
