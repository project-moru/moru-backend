package com.project.moru.repository;

import com.project.moru.domain.entity.card.Card;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CardRepository extends JpaRepository<Card, Long> {
}
