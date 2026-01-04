package com.project.moru.data_field.service.impl;

import com.project.moru.common.exception.ErrorCode;
import com.project.moru.common.exception.GeneralException;
import com.project.moru.data_field.domain.dto.create.LinkCreateRequestDto;
import com.project.moru.data_field.domain.dto.response.LinkDetailResponseDto;
import com.project.moru.data_field.domain.dto.response.LinkResponseDto;
import com.project.moru.data_field.domain.dto.update.LinkUpdateRequestDto;
import com.project.moru.data_field.domain.entity.DataField;
import com.project.moru.data_field.domain.entity.LinkBlock;
import com.project.moru.data_field.mapper.LinkBlockConverter;
import com.project.moru.data_field.service.LinkBlockService;
import com.project.moru.data_field.service_data.DataFieldDataService;
import com.project.moru.data_field.service_data.LinkBlockDataService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
@Transactional
public class LinkBlockServiceImpl implements LinkBlockService {
  
  private final LinkBlockDataService linkBlockDataService;
  private final LinkBlockConverter linkBlockConverter;
  
  private final DataFieldDataService dataFieldDataService;
  
  @Override
  public List<LinkDetailResponseDto> getListByDataField(Long dataFieldId) {
    return linkBlockConverter.toDetailDtoList(
        linkBlockDataService.findLinksByDataFieldId(dataFieldId)
    );
  }
  
  @Override
  public LinkResponseDto register(Long dataFieldId, LinkCreateRequestDto dto, Long userId) {
    DataField dataField = dataFieldDataService.findById(dataFieldId);
    
    if (!dataField.getUser().getId().equals(userId)) {
      throw new GeneralException(ErrorCode.ACCESS_DENIED);
    }
    
    LinkBlock linkBlock = linkBlockConverter.toEntity(dto);
    dataField.addLink(linkBlock);
    linkBlock = linkBlockDataService.save(linkBlock);
    return linkBlockConverter.toDto(linkBlock);
  }
  
  @Override
  public LinkResponseDto update(Long linkBlockId, LinkUpdateRequestDto dto) {
    LinkBlock linkBlock = linkBlockDataService.findById(linkBlockId);
    linkBlock.update(dto);
    return linkBlockConverter.toDto(linkBlock);
  }
  
  @Override
  public void delete(Long linkBlockId) {
    linkBlockDataService.deleteById(linkBlockId);
  }
}
