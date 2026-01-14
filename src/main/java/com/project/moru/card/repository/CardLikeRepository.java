package com.project.moru.card.repository;

import com.project.moru.card.domain.entity.CardLike;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CardLikeRepository extends JpaRepository<CardLike, Long> {
}
