package com.project.moru.domain.deck.repository;

import com.project.moru.domain.deck.entity.Deck;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DeckRepository extends JpaRepository<Deck, Long> {
    List<Deck> findAllByUserId(Long userId);
}
