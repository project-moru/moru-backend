package com.project.moru.cardlink.domain.entity;

import com.project.moru.card.domain.entity.Card;
import com.project.moru.common.domain.entity.BaseEntity;
import com.project.moru.data_field.domain.entity.LinkBlock;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;

@SuperBuilder
@NoArgsConstructor
@Entity
@Getter
@Table(name = "card_link_block")
public class CardLinkBlock extends BaseEntity {
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "card_id")
  private Card card;
  
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "link_block_id")
  private LinkBlock linkBlock;
}
