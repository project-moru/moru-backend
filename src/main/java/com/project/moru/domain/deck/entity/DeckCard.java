package com.project.moru.domain.deck.entity;

import com.project.moru.domain.entity.BaseEntity;
import com.project.moru.domain.entity.card.Card;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "deck_card")
public class DeckCard extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "deck_id")
    private Deck deck;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "card_id")
    private Card card;
}
