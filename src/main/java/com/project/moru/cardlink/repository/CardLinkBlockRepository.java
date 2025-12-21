package com.project.moru.cardlink.repository;

import com.project.moru.card.domain.entity.Card;
import com.project.moru.cardlink.domain.entity.CardLinkBlock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CardLinkBlockRepository extends JpaRepository<CardLinkBlock, Long> {
  List<CardLinkBlock> findAllByLinkBlockId(Long linkBlockId);
  
  Long card(Card card);
}