package com.project.moru.data_field.service_data;

import com.project.moru.data_field.domain.entity.LinkBlock;

import java.util.List;

public interface LinkBlockDataService {
  LinkBlock findById(Long linkBlockId);
  LinkBlock save(LinkBlock linkBlock);
  void deleteById(Long linkBlockId);
  List<LinkBlock> findLinksByDataFieldId(Long dataFieldId);
}
