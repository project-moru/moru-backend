package com.project.moru.data_field.domain.entity;

import com.project.moru.common.domain.entity.BaseEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;

@SuperBuilder(toBuilder = true)
@NoArgsConstructor
@Entity
@Getter
@Table(name = "data_field_link_block")
public class LinkBlock extends BaseEntity {
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "data_field_id", nullable = false)
  private DataField dataField;
  
  @Column(nullable = false)
  private String name;
  
  @Column(name = "max_link_count")
  private Integer maxLinkCount;
  
  public void update() {
    // 구현 필요
  }
  
  public void setDataField(DataField dataField) {
    this.dataField = dataField;
    if (!dataField.getLinkBlocks().contains(this)) {
      dataField.getLinkBlocks().add(this);
    }
  }
}
