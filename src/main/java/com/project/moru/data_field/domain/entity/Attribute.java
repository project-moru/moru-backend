package com.project.moru.data_field.domain.entity;

import com.project.moru.data_field.constant.Type;
import com.project.moru.data_field.domain.dto.AttributeUpdateRequestDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Optional;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Getter
@Table(name = "attributes")
public class Attribute {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "data_field_id", nullable = false)
  private DataField dataField;
  
  @Column(nullable = false)
  private String name;
  
  @Column(name = "place_holder", nullable = false)
  private String placeHolder;
  
  @Enumerated(EnumType.STRING)
  private Type type;
  
  public void update(AttributeUpdateRequestDto dto) {
    Optional.ofNullable(dto.getName())
        .filter(name -> !name.isBlank())
        .ifPresent(name -> this.name = name);
    
    Optional.ofNullable(dto.getPlaceHolder())
        .filter(ph -> !ph.isBlank())
        .ifPresent(ph -> this.placeHolder = ph);
    
    Optional.ofNullable(dto.getType())
        .ifPresent(type -> this.type = type);
  }
}
