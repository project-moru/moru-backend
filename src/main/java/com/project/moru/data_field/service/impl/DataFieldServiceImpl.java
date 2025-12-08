package com.project.moru.data_field.service.impl;

import com.project.moru.data_field.domain.dto.*;
import com.project.moru.data_field.domain.entity.DataField;
import com.project.moru.data_field.mapper.DataFieldConverter;
import com.project.moru.data_field.service.DataFieldService;
import com.project.moru.data_field.service_data.DataFieldDataService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
@Transactional
public class DataFieldServiceImpl implements DataFieldService {
  
  private final DataFieldDataService dataFieldDataService;
  private final DataFieldConverter dataFieldConverter;
  
  @Override
  @Transactional(readOnly = true)
  public List<DataFieldResponseDto> getListByUser(Long userId) {
    return dataFieldConverter.toDtoList(
        dataFieldDataService.findDataFieldsByUserId(userId)
    );
  }
  
  @Override
  public DataFieldResponseDto register(DataFieldCreateRequestDto dto) {
    DataField dataField = dataFieldConverter.toEntity(dto);
    return dataFieldConverter.toDto(dataFieldDataService.save(dataField));
  }
  
  @Override
  public DataFieldResponseDto update(Long dataFieldId, DataFieldUpdateRequestDto dto) {
    DataField dataField = dataFieldDataService.findById(dataFieldId);
    dataField.update(dto);
    return dataFieldConverter.toDto(dataField);
  }
  
  @Override
  public void delete(Long dataFieldId) {
    dataFieldDataService.deleteById(dataFieldId);
  }
}
