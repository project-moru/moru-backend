package com.project.moru.data_field.service;

import com.project.moru.data_field.domain.dto.DataFieldCreateRequestDto;
import com.project.moru.data_field.domain.dto.DataFieldResponseDto;
import com.project.moru.data_field.domain.dto.DataFieldUpdateRequestDto;

import java.util.List;

public interface DataFieldService {
  List<DataFieldResponseDto> getListByUser(Long userId);
  DataFieldResponseDto register(DataFieldCreateRequestDto dto);
  DataFieldResponseDto update(Long dataFieldId, DataFieldUpdateRequestDto dto);
  void delete(Long dataFieldId);
}
