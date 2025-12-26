package com.project.moru.data_field.service;

import com.project.moru.data_field.domain.dto.create.DataFieldBundleCreateRequestDto;
import com.project.moru.data_field.domain.dto.response.DataFieldDetailResponseDto;
import com.project.moru.data_field.domain.dto.response.DataFieldResponseDto;
import com.project.moru.data_field.domain.dto.update.DataFieldUpdateRequestDto;

import java.util.List;

public interface DataFieldService {
  List<DataFieldResponseDto> getListByUser(Long userId);
  DataFieldDetailResponseDto getDataFieldById(Long dataFieldId, Long userId);
  DataFieldResponseDto register(DataFieldBundleCreateRequestDto dto, Long userId);
  DataFieldResponseDto update(Long dataFieldId, DataFieldUpdateRequestDto dto, Long userId);
  void delete(Long dataFieldId, Long userId);
}
