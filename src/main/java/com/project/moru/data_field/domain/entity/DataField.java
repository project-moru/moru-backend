package com.project.moru.data_field.domain.entity;

import com.project.moru.data_field.domain.dto.AttributeUpdateRequestDto;
import com.project.moru.data_field.domain.dto.DataFieldUpdateRequestDto;
import com.project.moru.user.domain.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Getter
@Table(name = "data_field")
public class DataField {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "user_id",
      foreignKey = @ForeignKey(name = "fk_data_field_user"))
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
}