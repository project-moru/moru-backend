package com.project.moru.card.repository;

import com.project.moru.card.domain.entity.Card;
import com.project.moru.common.constant.Status;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CardRepository extends JpaRepository<Card, Long> {

    List<Card> findAllByUser_IdOrStatus(Long userId, Status status);
}
