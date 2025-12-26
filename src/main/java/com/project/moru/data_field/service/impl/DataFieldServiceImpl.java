package com.project.moru.data_field.service.impl;

import com.project.moru.common.exception.ErrorCode;
import com.project.moru.common.exception.GeneralException;
import com.project.moru.data_field.domain.dto.create.DataFieldBundleCreateRequestDto;
import com.project.moru.data_field.domain.dto.create.DataFieldCreateRequestDto;
import com.project.moru.data_field.domain.dto.response.DataFieldDetailResponseDto;
import com.project.moru.data_field.domain.dto.response.DataFieldResponseDto;
import com.project.moru.data_field.domain.dto.update.DataFieldUpdateRequestDto;
import com.project.moru.data_field.domain.entity.DataField;
import com.project.moru.data_field.mapper.AttributeBlockConverter;
import com.project.moru.data_field.mapper.DataFieldConverter;
import com.project.moru.data_field.mapper.LinkBlockConverter;
import com.project.moru.data_field.service.AttributeBlockService;
import com.project.moru.data_field.service.DataFieldService;
import com.project.moru.data_field.service.LinkBlockService;
import com.project.moru.data_field.service_data.DataFieldDataService;
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
  private final AttributeBlockService attributeBlockService;
  private final LinkBlockService linkBlockService;
  private final DataFieldConverter dataFieldConverter;
  private final AttributeBlockConverter attributeBlockConverter;
  private final LinkBlockConverter linkBlockConverter;
  private final UserDataService userDataService;
  
  @Override
  public DataFieldResponseDto register(DataFieldBundleCreateRequestDto dto, Long userId) {
    
    // 1. DataField 생성
    DataFieldCreateRequestDto dataFieldDto = dto.getDataField();
    DataField dataField = dataFieldConverter.toEntity(
        dataFieldDto,
        userDataService.findUserById(userId)
            .orElseThrow(() -> new GeneralException(ErrorCode.NOT_FOUND_USER))
    );
    DataField savedDataField = dataFieldDataService.save(dataField);
    
    // 2. 속성블록 확인
    if (dto.getAttributeBlocks() != null) {
      dto.getAttributeBlocks().forEach(attr ->
          savedDataField.addAttribute(
              attributeBlockConverter.toEntity(attr)
          )
      );
    }
    
    // 3. 연결블록 확인
    if (dto.getLinkBlocks() != null) {
      dto.getLinkBlocks().forEach(link ->
        savedDataField.addLink(
            linkBlockConverter.toEntity(link)
        )
      );
    }
    
    return dataFieldConverter.toDto(savedDataField);
  }
  
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
        .attributeBlocks(attributeBlockService.getListByDataField(dataFieldId))
        .linkBlocks(linkBlockService.getListByDataField(dataFieldId))
        .build();
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
