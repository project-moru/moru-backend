package com.project.moru.data_field.service;

import com.project.moru.data_field.domain.dto.create.LinkCreateRequestDto;
import com.project.moru.data_field.domain.dto.response.LinkDetailResponseDto;
import com.project.moru.data_field.domain.dto.response.LinkResponseDto;
import com.project.moru.data_field.domain.dto.update.LinkUpdateRequestDto;

import java.util.List;

public interface LinkBlockService {
  List<LinkDetailResponseDto> getListByDataField(Long dataFieldId);
  LinkResponseDto register(Long dataFieldId, LinkCreateRequestDto dto, Long userId);
  LinkResponseDto update(Long linkBlockId, LinkUpdateRequestDto dto);
  void delete(Long linkBlockId);
}
