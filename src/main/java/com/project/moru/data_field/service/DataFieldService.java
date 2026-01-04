package com.project.moru.data_field.service;

import com.project.moru.data_field.domain.dto.create.DataFieldBundleCreateRequestDto;
import com.project.moru.data_field.domain.dto.response.DataFieldDetailResponseDto;
import com.project.moru.data_field.domain.dto.response.DataFieldListResponseDto;
import com.project.moru.data_field.domain.dto.response.DataFieldResponseDto;
import com.project.moru.data_field.domain.dto.update.DataFieldBundleUpdateRequestDto;
import com.project.moru.user.domain.entity.CustomUserDetails;

public interface DataFieldService {
  DataFieldListResponseDto getListByUser(Long userId);
  DataFieldDetailResponseDto getDataFieldById(Long dataFieldId, Long userId);
  DataFieldResponseDto register(DataFieldBundleCreateRequestDto dto, Long userId);
  DataFieldResponseDto update(Long dataFieldId, DataFieldBundleUpdateRequestDto dto, Long userId);
  void delete(Long dataFieldId, CustomUserDetails userDetails);
}
