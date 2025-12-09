package com.project.moru.data_field.domain.entity;

import com.project.moru.common.domain.entity.BaseEntity;
import com.project.moru.data_field.domain.dto.DataFieldUpdateRequestDto;
import com.project.moru.user.domain.entity.User;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
  
  @OneToMany(mappedBy = "dataField", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<Attribute> attributes = new ArrayList<>();
  
  public void update(DataFieldUpdateRequestDto dto) {
    Optional.ofNullable(dto.getName())
        .filter(name -> !name.isBlank())
        .ifPresent(name -> this.name = name);
    
    Optional.ofNullable(dto.getDescription())
        .filter(description -> !description.isBlank())
        .ifPresent(description -> this.description = description);
  }
  
  public void addAttribute(Attribute attribute) {
    attributes.add(attribute);
    attribute.setDataField(this);
  }
}