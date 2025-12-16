package com.project.moru.data_field.service;

import com.project.moru.data_field.domain.dto.LinkCreateRequestDto;
import com.project.moru.data_field.domain.dto.LinkResponseDto;
import com.project.moru.data_field.domain.dto.LinkUpdateRequestDto;

import java.util.List;

public interface LinkBlockService {
  List<LinkResponseDto> getListByDataField(Long dataFieldId);
  LinkResponseDto register(LinkCreateRequestDto dto, Long userId);
  LinkResponseDto update(Long linkBlockId, LinkUpdateRequestDto dto);
  void delete(Long linkBlockId);
}
