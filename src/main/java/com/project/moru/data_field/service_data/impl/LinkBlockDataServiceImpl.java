package com.project.moru.data_field.service_data.impl;

import com.project.moru.data_field.domain.entity.LinkBlock;
import com.project.moru.data_field.repository.LinkBlockRepository;
import com.project.moru.data_field.service_data.LinkBlockDataService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class LinkBlockDataServiceImpl implements LinkBlockDataService {
  
  private final LinkBlockRepository linkBlockRepository;
  
  @Override
  public LinkBlock findById(Long linkBlockId) {
    return linkBlockRepository.findById(linkBlockId)
        .orElseThrow();
  }
  
  @Override
  public LinkBlock save(LinkBlock linkBlock) {
    return linkBlockRepository.save(linkBlock);
  }
  
  @Override
  public void deleteById(Long linkBlockId) {
    linkBlockRepository.deleteById(linkBlockId);
  }
  
  @Override
  public List<LinkBlock> findLinksByDataFieldId(Long dataFieldId) {
    return linkBlockRepository.findLinkBlocksByDataFieldId(dataFieldId);
  }
}
