package com.project.moru.deck.repository;

import com.project.moru.deck.domain.entity.DeckCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeckCardRepository extends JpaRepository<DeckCard, Long> {
}
