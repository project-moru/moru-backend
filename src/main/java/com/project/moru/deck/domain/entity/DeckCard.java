package com.project.moru.deck.domain.entity;

import com.project.moru.common.domain.entity.BaseEntity;
import com.project.moru.card.domain.entity.Card;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;

@SuperBuilder
@NoArgsConstructor
@Entity
@Getter
@Table(name = "deck_card")
public class DeckCard extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "deck_id")
    private Deck deck;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "card_id")
    private Card card;
}