package com.project.moru.data_field.service.impl;

import com.project.moru.common.exception.ErrorCode;
import com.project.moru.common.exception.GeneralException;
import com.project.moru.data_field.domain.dto.*;
import com.project.moru.data_field.domain.entity.DataField;
import com.project.moru.data_field.mapper.DataFieldConverter;
import com.project.moru.data_field.service.AttributeService;
import com.project.moru.data_field.service.DataFieldService;
import com.project.moru.data_field.service_data.AttributeDataService;
import com.project.moru.data_field.service_data.DataFieldDataService;
import com.project.moru.user.domain.entity.User;
import com.project.moru.user.service.UserService;
import com.project.moru.user.service_data.UserDataService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
@Transactional
public class DataFieldServiceImpl implements DataFieldService {
  
  private final DataFieldDataService dataFieldDataService;
  private final AttributeService attributeService;
  private final DataFieldConverter dataFieldConverter;
  private final UserDataService userDataService;
  
  @Override
  @Transactional(readOnly = true)
  public List<DataFieldResponseDto> getListByUser(Long userId) {
    return dataFieldConverter.toDtoList(
        dataFieldDataService.findDataFieldsByUserId(userId)
    );
  }
  
  @Override
  @Transactional(readOnly = true)
  public DataFieldDetailResponseDto getDataFieldById(Long dataFieldId, Long userId) {
    DataField dataField = dataFieldDataService.findById(dataFieldId);
    if (!dataField.getUser().getId().equals(userId)) {
      throw new GeneralException(ErrorCode.ACCESS_DENIED);
    }
    
    return DataFieldDetailResponseDto.builder()
        .dataField(dataFieldConverter.toDto(dataField))
        .attributeBlocks(attributeService.getListByDataField(dataFieldId))
        .build();
  }
  
  @Override
  public DataFieldResponseDto register(DataFieldCreateRequestDto dto, Long userId) {
    DataField dataField = dataFieldConverter.toEntity(dto, userDataService.findUserById(userId).orElseThrow(
        () -> new GeneralException(ErrorCode.NOT_FOUND_USER)
    ));
    return dataFieldConverter.toDto(dataFieldDataService.save(dataField));
  }
  
  @Override
  public DataFieldResponseDto update(Long dataFieldId, DataFieldUpdateRequestDto dto, Long userId) {
    DataField dataField = dataFieldDataService.findById(dataFieldId);
    
    if (!dataField.getUser().getId().equals(userId)) {
      throw new GeneralException(ErrorCode.ACCESS_DENIED);
    }
    
    dataField.update(dto);
    return dataFieldConverter.toDto(dataField);
  }
  
  @Override
  public void delete(Long dataFieldId, Long userId) {
    DataField dataField = dataFieldDataService.findById(dataFieldId);
    
    if (dataField.getUser().getId().equals(userId)) {
      dataFieldDataService.deleteById(dataFieldId);
    } else {
      throw new GeneralException(ErrorCode.ACCESS_DENIED);
    }
  }
}
