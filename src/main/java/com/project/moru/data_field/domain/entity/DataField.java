package com.project.moru.data_field.domain.entity;

import com.project.moru.common.domain.entity.BaseEntity;
import com.project.moru.data_field.domain.dto.update.AttributeUpdateRequestDto;
import com.project.moru.data_field.domain.dto.update.DataFieldUpdateRequestDto;
import com.project.moru.data_field.domain.dto.update.LinkUpdateRequestDto;
import com.project.moru.user.domain.entity.User;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

@SuperBuilder(toBuilder = true)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Getter
@Table(name = "data_field")
public class DataField extends BaseEntity {
  
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "user_id", nullable = false)
  private User user;
  
  @Column(nullable = false)
  private String name;
  
  @Column(columnDefinition = "TEXT")
  private String description;
  
  @Builder.Default
  @OneToMany(mappedBy = "dataField", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<AttributeBlock> attributeBlocks = new ArrayList<>();
  
  @Builder.Default
  @OneToMany(mappedBy = "dataField", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<LinkBlock> linkBlocks = new ArrayList<>();
  
  public void update(DataFieldUpdateRequestDto dto) {
    Optional.ofNullable(dto.getName())
        .filter(name -> !name.isBlank())
        .ifPresent(name -> this.name = name);
    
    Optional.ofNullable(dto.getDescription())
        .filter(description -> !description.isBlank())
        .ifPresent(description -> this.description = description);
  }
  
  public void updateLinkBlocks(List<LinkUpdateRequestDto> dtos) {
    Map<String, LinkBlock> existing =
        this.linkBlocks.stream()
            .collect(Collectors.toMap(
                LinkBlock::getName,
                Function.identity()
            ));
    
    this.linkBlocks.clear();
    
    for (LinkUpdateRequestDto dto : dtos) {
      
      LinkBlock block =
          existing.getOrDefault(dto.getName(), new LinkBlock());
      
      block.update(dto);
      block.setDataField(this);
      
      this.linkBlocks.add(block);
    }
  }
  
  public void updateAttributeBlocks(List<AttributeUpdateRequestDto> dtos) {
    Map<String, AttributeBlock> existing =
        this.attributeBlocks.stream()
            .collect(Collectors.toMap(
                AttributeBlock::getName,
                Function.identity()
            ));
    
    this.attributeBlocks.clear();
    
    for (AttributeUpdateRequestDto dto : dtos) {
      
      AttributeBlock block =
          existing.getOrDefault(dto.getName(), new AttributeBlock());
      
      block.update(dto);
      block.setDataField(this);
      
      this.attributeBlocks.add(block);
    }
  }
  
  public void addAttribute(AttributeBlock attributeBlock) {
    attributeBlocks.add(attributeBlock);
    attributeBlock.setDataField(this);
  }
  
  public void addLink(LinkBlock linkBlock) {
    linkBlocks.add(linkBlock);
    linkBlock.setDataField(this);
  }
}