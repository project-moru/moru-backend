package com.project.moru.data_field.domain.entity;

import com.project.moru.user.domain.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

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
}